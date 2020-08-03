package com.project.Link.Admin.Manage.Noticement.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.Link.Admin.Manage.Noticement.Dao.ManageNoticementDao;
import com.project.Link.Admin.Manage.Noticement.Dao.ManageNoticementDaoImple;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDao;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementServiceImple;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("manageNoticeService")
public class ManageNoticementServiceImple extends NoticementServiceImple implements ManageNoticementService{
	private static final Logger logger = LoggerFactory.getLogger(NoticementServiceImple.class);
	private int pagePerBlock = 8;
	private static final String targetBoard = "noticement";
	private static final String targetBoardFile = targetBoard+"file";
	private static final String nFilePath = "C:\\temp\\" + targetBoard + "\\";
	private String prefix = "n_";


	@Autowired
	@Qualifier("manageNoticeDao")
	private ManageNoticementDao mnDao;
	@Autowired
	private UfileService ufService;
	
	public ManageNoticementDao getMnDao() {
		return mnDao;
	}

	public void setMnDao(ManageNoticementDao mnDao) {
		this.mnDao = mnDao;
	}

	public UfileService getUfService() {
		return ufService;
	}

	public void setUfService(UfileService ufService) {
		this.ufService = ufService;
	}
	// 공지사항 입력 요청을 수행하는 service입니다.
	// 이때, 파일 upload를 동시에합니다.
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public boolean createNoticement(String usrId, String title, String contents, List<MultipartFile> uFileList) throws Exception{
		logger.info("			ServiceLevelCalled ::::::: createNoticement");
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		Iterator<MultipartFile> iterator = uFileList.iterator();
		while(iterator.hasNext()) {
			MultipartFile mf = iterator.next();
			if(mf.getOriginalFilename() == "" || mf.getOriginalFilename() == null){
				iterator.remove();
			}
		}
		int serial = mnDao.getLastSerial(targetBoard, prefix)+1;
		boolean result;
		try {
			// 파일을 DB에 등록
			result = mnDao.createNoticement(targetBoard, prefix, serial, usrId, title, contents, uFileList.size(), createDate) >=1 ? true : false;
			File file = new File(nFilePath);
			if(file.exists() == false){
				file.mkdirs(); 
			}
			// 파일 서버에 저장 후, 등록
			if(result==true) {
				for(MultipartFile mf : uFileList) {	
					String originalFileName = mf.getOriginalFilename();
					long fileSize = mf.getSize();
					String modifiedFileName = System.currentTimeMillis()+originalFileName;
					try {
						mf.transferTo(new File(nFilePath+modifiedFileName));
						//uFileService는 Notice, Community에서 공통적으로 사용하기때문에, targetboard를 명시해야합니다.
						result = ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize, createDate, originalFileName, serial) >= 1 ? true : false;
					}catch(RuntimeException e) {
						throw e;
					}catch(IOException e) {
						throw e;
					}
				}
			}else {
				return false;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw e1;
		}
		return result;
	}
	
	//공지사항 업데이트 요청에 응답하는 endpoint입니다.
	@Transactional(rollbackFor = RuntimeException.class)
	@Override
	public boolean updateNoticement(
			String usrId, int serial, String title, String contents,
			List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFileList
			) throws Exception{
		logger.info("			ServiceLevelCalled ::::::: UpdateNoticement Called");
		try {
				//Integer.valueOf((String)mpRequest.getParameter("f_count"));
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		
		int previousListSize = 0;
		int ufileListSize = 0;
		int deleteTargetSize = 0;

		// 기존의 입력되어있는 파일에 대해서 작업을 진행합니다.
		// text형태로 파일 코드만 전송받으며 filecount 변경에 쓰입니다.
		if(previousFileCodes != null) {	
			Iterator<String> iterator = previousFileCodes.iterator();
			while(iterator.hasNext()) {
				String smf = iterator.next();
				if(smf==null || smf=="" || smf.isEmpty()){
					iterator.remove();
				}
			}
			previousListSize = previousFileCodes.size();

		}
		// 공지사항-파일 간 연결 해제 요청을 수행합니다.
		if(deleteFileCodes!=null) {
			Iterator<String> iterator = deleteFileCodes.iterator();
			System.out.println("deletefilecoesfiaaa : " + deleteFileCodes.size());
			Timestamp disconnDate = Timestamp.valueOf(LocalDateTime.now());
			while(iterator.hasNext()) {
				String smf = iterator.next();
				if(smf==null || smf=="" || smf.isEmpty()){
					iterator.remove();
				}else {
					// 존재하는 파일에 대해 연결을 바로 끊습니다.
					logger.info("			ServiceLevelCalled ::::::: uFileDetach Called");
					ufService.uFileDetach(targetBoardFile, smf, usrId, disconnDate);
				}
			}
			deleteTargetSize = deleteFileCodes.size();
		}
		//  신규 등록요청 파일이 있는지 확인합니다.
		if(uFileList!=null) {
			Iterator<MultipartFile> iterator = uFileList.iterator();
			File file = new File(nFilePath);
			if(file.exists() == false){ file.mkdirs(); }
			while(iterator.hasNext()) {
				MultipartFile mf = iterator.next();
				if(mf.getOriginalFilename() == "" || mf.getOriginalFilename() == null){
					iterator.remove();
				}else {
					String originalFileName = mf.getOriginalFilename();
					long fileSize = mf.getSize();
					String modifiedFileName = System.currentTimeMillis()+originalFileName;
					try {
						// 디운로드 완료한 파일을 서버에 저장합니다.
						mf.transferTo(new File(nFilePath+modifiedFileName));
						logger.info("			ServiceLevelCalled ::::::: uFileUpload Called");
						// 이후, 서버 파일 DB에 정보를 등록합니다.
						ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize, createDate, originalFileName, serial);
					}catch(RuntimeException e) {
						throw e;
					}catch(IOException e) {
						throw e;
					}	
				}
			}
			ufileListSize = uFileList.size();
		}
		logger.info("			ServiceLevelCalled ::::::: UploadNoticementCalled Finally Called");
		int fileCount = previousListSize - deleteTargetSize + ufileListSize;
		//파일 요청에 대한 작업이 모두 끝났다면 공지사항의 변경내역을 적용합니다.
		mnDao.updateNoticement(usrId, serial, title, contents, fileCount, modifyDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// 공지사항 삭제요청을 수행합니다.
	@Override
	public boolean deleteNoticement(int targetSerial, String usrId) {
		
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = mnDao.deleteNoticement(usrId, targetSerial, deleteDate) >= 1 ? true : false;
		return result;
	}
}
