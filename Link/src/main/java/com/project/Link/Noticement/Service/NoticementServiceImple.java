package com.project.Link.Noticement.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Noticement.Dao.NoticementDao;
import com.project.Link.Noticement.Dao.NoticementDaoImple;
import com.project.Link.Posting.Posting;
import com.project.Link.Ufile.Dao.UfileDao;
import com.project.Link.Ufile.Service.UfileService;

@Service
public class NoticementServiceImple implements NoticementService{
	private static final Logger logger = LoggerFactory.getLogger(NoticementServiceImple.class);
	private int pagePerBlock = 8;
	private static final String targetBoard = "noticement";
	private static final String nFilePath = "C:\\temp\\" + targetBoard + "\\";
	private String prefix = "n";
	
	@Autowired
	private NoticementDao nDao;
	@Autowired
	private UfileService ufService;
	
	public NoticementServiceImple () {};
	public NoticementDao getnDao() {return nDao;}
	public void setnDao(NoticementDao nDao) {this.nDao = nDao;}

	@Override
	public int totalCountNoticements() {
		int totalCount = nDao.getTotalCount();
		return totalCount;
	}
	
	@Override
	public ArrayList<Posting> listNoticements(HttpServletRequest request) {
		logger.info("::listNoticements called");
		System.out.println(request.getParameter("page"));
		
		int targetPage = request.getParameter("page") == null ? 1 :Integer.parseInt(request.getParameter("page"));
		// 프론트엔 1부터로 출력되지만 DB Limit 구문이 시작점을 인식하므로, 0으로 변환
		/* targetPage = Integer.valueOf(request.getParameter("page"))-1; */
		ArrayList<Posting> list = nDao.getListPosting(targetPage, pagePerBlock);
		return list;
	}
	
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	//public boolean createNoticement(String usrId, String ntcTitle, String ntcContents, List<MultipartFile> ufilelist) {
	public boolean createNoticement(MultipartHttpServletRequest mpRequest, HttpSession session) throws Exception{
		logger.info("::createNoticement called");
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		String usrId = (String)session.getAttribute("usrId");
		String title = mpRequest.getParameter("n_title");
		String contents = mpRequest.getParameter("n_contents");
		List<MultipartFile> ufileList = mpRequest.getFiles("u_files");
		logger.info("\nfileInfocome"+ufileList.size()+"\n");
		logger.info("==============================================");
		logger.info(ufileList.get(0).getOriginalFilename());
		logger.info("==============================================");
		int serial = nDao.getLastSerial()+1;
		boolean result;
		try {
			int fileCount = 0;
			for(MultipartFile mf : ufileList) {
				if(!(mf.isEmpty())) {
					fileCount += 1;
				}
			}
			result = nDao.createPosting(serial, usrId, title, contents, fileCount, createDate) >=1 ? true : false;
			File file = new File(nFilePath);
			if(file.exists() == false){ file.mkdirs(); }

			for(MultipartFile mf : ufileList) {
				if(mf.isEmpty()) {
					logger.info("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
					logger.info("isempty called");
					continue;
				}
				String originalFileName = mf.getOriginalFilename();
				/*
				 * if(originalFileName=="" || originalFileName == null) {
				 * logger.info("******************************"); logger.info("NULL called"); }
				 */
				long fileSize = mf.getSize();
				String modifiedFileName = nFilePath+System.currentTimeMillis()+originalFileName;
				try {
					mf.transferTo(new File(modifiedFileName));
					//uFileService는 Notice, Community에서 공통적으로 사용하기때문에, targetboard를 명시해야합니다.
					result = ufService.uFileUpload(targetBoard, modifiedFileName, usrId, fileSize, createDate, originalFileName, serial) >= 1 ? true : false;
				}catch(RuntimeException e) {
					throw e;
				}catch(IOException e) {
					throw e;
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw e1;
		}
		return result;
	}

	//Get 요청시 파일정보도 같이줘야합니다.
	@Override
	public Posting getNoticement(HttpServletRequest request) {
		logger.info("::getNoticement called");
		int targetSerial = Integer.valueOf(request.getParameter("serial"));
		Posting targetPosting = nDao.getPosting(targetSerial);
		nDao.countUp(targetBoard, targetSerial, targetPosting.getNoticeCount() +1);
		targetPosting.setuFileList(ufService.uFileGet(targetBoard, targetSerial));
		logger.warn(":: getNoticement File list length: " + targetPosting.getuFileList().size());
		targetPosting.setNoticeCount( targetPosting.getNoticeCount() +1);
		return targetPosting;
	}

	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	/*
	 * public boolean updateNoticement(int targetSerial, String ntcTitle, String
	 * ntcContents, int fileCount) {
	 */
	public boolean updateNoticement(HttpSession session,MultipartHttpServletRequest mpRequest) throws Exception{
		logger.info("::updateNoticement called");
		
		String usrId = (String)session.getAttribute("usrId");
		int serial = Integer.valueOf((String)mpRequest.getParameter("n_serial"));
		String title = mpRequest.getParameter("n_title");
		String contents = mpRequest.getParameter("n_contents");
		int fileCount = Integer.valueOf((String)mpRequest.getParameter("f_count"));
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		
		List<MultipartFile> ufileList = mpRequest.getFiles("u_files");
		boolean result = nDao.updatePosting(usrId,serial, title, contents, fileCount, modifyDate) >= 1 ? true : false;
		//TODO File Update는 Community까지 끝내고 생각해보자.
		/*
		 * try { for(MultipartFile mf : ufileList) { String originalFileName =
		 * mf.getOriginalFilename(); long fileSize = mf.getSize(); String
		 * modifiedFileName = nFilePath+System.currentTimeMillis()+originalFileName; try
		 * { mf.transferTo(new File(modifiedFileName)); //uFileService는 Notice,
		 * Community에서 공통적으로 사용하기때문에, targetboard를 명시해야합니다. result =
		 * ufService.uFileUpload(targetBoard, modifiedFileName, usrId, fileSize,
		 * createDate, originalFileName, serial) >= 1 ? true : false;
		 * }catch(RuntimeException e) { throw e; }catch(IOException e) { throw e; } } }
		 * catch (Exception e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); throw e1; }
		 */
		return result;
	}

	@Override
	public boolean deleteNoticement(HttpSession session, HttpServletRequest request) {
		int targetSerial = Integer.valueOf(request.getParameter("serial"));
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		String usrId = (String)session.getAttribute("usrId");
		boolean result = nDao.deletePosting(usrId, targetSerial, deleteDate) >= 1 ? true : false;
		return result;
	}

	

}
