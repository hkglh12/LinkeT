package com.project.Link.Community.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Comment.Service.CommentService;
import com.project.Link.Community.Community;
import com.project.Link.Community.Dao.CommunityDao;
import com.project.Link.Noticement.Controller.NoticementControllerImple;
import com.project.Link.Noticement.Service.NoticementServiceImple;
import com.project.Link.Posting.Posting;
import com.project.Link.Ufile.Service.UfileService;

@Service
public class CommunityServiceImple implements CommunityService{
	private static final Logger logger = LoggerFactory.getLogger(NoticementServiceImple.class);
	private int pagePerBlock=10;
	private static final String targetBoard = "community";
	private static final String targetBoardFile = targetBoard+"file";
	private static final String cFilePath = "C:\\temp\\" + targetBoard + "\\";
	private String prefix = "c_";
	
	@Autowired
	private CommunityDao cDao; 
	@Autowired
	private UfileService ufService;
	@Autowired
	private CommentService ccService;
	
	
	public CommunityServiceImple() {}
	public CommunityDao getcDao() {return cDao;}
	public void setcDao(CommunityDao cDao) {this.cDao = cDao;}
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}

	@Override
	public int totalCountCommunities() {
		int totalCount = cDao.getTotalCount(targetBoard, prefix);
		return totalCount;
	}
	
	@Override
	public ArrayList<Community> ListCommunities(int targetPage) {
		logger.info("			ServiceLevelCalled ::::::: ListCommunities");
		ArrayList<Community> list = cDao.getListCommunity(targetPage, pagePerBlock);
		return list;
	}
	//TODO 이아이는 Transactional이어야 합니다.
	
	@Override
	public boolean createCommunity(String usrId, String title, String contents, List<MultipartFile> uFileList) throws Exception {
		logger.info("			ServiceLevelCalled ::::::: CreateCommunity");
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		Iterator<MultipartFile> iterator = uFileList.iterator();
		while(iterator.hasNext()) {
			MultipartFile mf = iterator.next();
			if(mf.getOriginalFilename() == "" || mf.getOriginalFilename() == null){
				iterator.remove();
			}
		}
		int serial = cDao.getLastSerial(targetBoard, prefix)+1;
		boolean result;
		try {
			result = cDao.createPosting(targetBoard, prefix, serial, usrId, title, contents, uFileList.size(), createDate) >=1 ? true : false;
			File file = new File(cFilePath);
			if(file.exists() == false){ 
				file.mkdirs(); 
			}
			for(MultipartFile mf : uFileList) {
				String originalFileName = mf.getOriginalFilename();
				long fileSize = mf.getSize();
				String modifiedFileName = System.currentTimeMillis()+originalFileName;
				try {
					mf.transferTo(new File(cFilePath+modifiedFileName));
					//uFileService는 Notice, Community에서 공통적으로 사용하기때문에, targetboard를 명시해야합니다.
					result = ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize, createDate, originalFileName, serial) >= 1 ? true : false;
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

	@Override
	public Community getCommunity(int targetSerial) {
		logger.info("			ServiceLevelCalled ::::::: getCommunity");
		
		Community targetCommunity = cDao.getCommunity(targetSerial);
		cDao.countUp(targetBoard, prefix, targetSerial, targetCommunity.getReadCount()+1);
		targetCommunity.setuFileList(ufService.uFileGet(targetBoardFile, targetSerial));
		// 댓글가져오는부분 점검하세요
		targetCommunity.setComments(ccService.ListCommunities(targetSerial));
		targetCommunity.setReadCount( targetCommunity.getReadCount() +1);
		return targetCommunity;
	}

	@Override
	public boolean updateCommunity(String usrId, int serial, String title, String contents,
			List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFileList) throws Exception{
		logger.info("::updateNoticement called");	
		try {
				//Integer.valueOf((String)mpRequest.getParameter("f_count"));
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		int previousListSize = 0;
		int ufileListSize = 0;
		int deleteTargetSize = 0;
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
		if(deleteFileCodes!=null) {
			Iterator<String> iterator = deleteFileCodes.iterator();
			Timestamp disconnDate = Timestamp.valueOf(LocalDateTime.now());
			while(iterator.hasNext()) {
				String smf = iterator.next();
				if(smf==null || smf=="" || smf.isEmpty()){
					iterator.remove();
				}else {
					ufService.uFileDetach(targetBoardFile, smf, usrId, disconnDate);
				}
			}
			deleteTargetSize = deleteFileCodes.size();
		}
		if(uFileList!=null) {
			Iterator<MultipartFile> iterator = uFileList.iterator();
			File file = new File(cFilePath);
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
						mf.transferTo(new File(cFilePath+modifiedFileName));
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
		int fileCount = previousListSize - deleteTargetSize + ufileListSize;
		//파일 자체의 업데이트를 끝내면
		cDao.updateCommunity(serial, title, contents, fileCount, modifyDate);
		//기존 파일이 있을경우 파일 연결을 끊어야하고
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteCommunity(int targetSerial) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = cDao.deleteCommunity(targetSerial, deleteDate) >= 1 ? true : false;
		return result;
	}
}


