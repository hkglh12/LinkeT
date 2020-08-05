package com.project.Link.RegUser.Community.Service;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Comment.Comment;
import com.project.Link.Comment.Service.CommentService;
import com.project.Link.Posting.Posting;
import com.project.Link.RegUser.Community.Community;
import com.project.Link.RegUser.Community.Dao.CommunityDao;
import com.project.Link.RegUser.Noticement.NoticementController.NoticementControllerImple;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementServiceImple;
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
	public CommentService getCcService() {return ccService;}
	public void setCcService(CommentService ccService) {this.ccService = ccService;}
	

	@Override
	public int userCountCommunities(String usrId) {
		//특정 유저가 작성한 댓글 개수를 가져옵니다.
		return cDao.userCountCommunities(usrId);
	}


	@Override
	public int totalCountCommunities(String searchCategory, String searchTarget, String subject) {
		// 전체 게시글의 개수를 가져옵니다.
		int totalCount = 0;
		if(searchTarget == null || searchCategory == null) {
			totalCount = cDao.getTotalCount(targetBoard, prefix, subject);
		}else {
			totalCount = cDao.getSearchCount(targetBoard, prefix, searchCategory, searchTarget, subject);
		}
		return totalCount;
	}
	
	@Override
	public ArrayList<Community> ListCommunities(int targetPage, String searchCategory, String searchTarget, String communitySubject) {
		// 게시글을 페이지단위로 DB로부터 추출합니다.
		ArrayList<Community> list = null;
		if(searchTarget == null || searchTarget == "") {
			// 검색 대상이 없을경우, 일반적인 추출을 시작합니다.
			list = cDao.getListCommunity(targetPage, pagePerBlock, communitySubject); 
		}else {
			// 검색 대상이 있을경우, 특정 타겟을 대상으로 추출합니다.
			list = cDao.searchListCommunity(targetPage, pagePerBlock, searchCategory, searchTarget, communitySubject);
		}
		return list;
	}
	
	@Transactional
	@Override
	public boolean createCommunity(String usrId, String title, String contents, List<MultipartFile> uFileList, String subject) throws Exception {
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
			result = cDao.createPosting(targetBoard, prefix, serial, usrId, title, contents, uFileList.size(), createDate, subject) >=1 ? true : false;
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
			e1.printStackTrace();
			throw e1;
		}
		return result;
	}

	@Override
	public Community getCommunity(int targetSerial) {
		Community targetCommunity = cDao.getCommunity(targetSerial);
		cDao.countUp(targetBoard, prefix, targetSerial, targetCommunity.getReadCount()+1);
		targetCommunity.setuFileList(ufService.uFileGet(targetBoardFile, targetSerial));
		targetCommunity.setReadCount(targetCommunity.getReadCount() +1);
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

	/*
	 * @Override public int getCommentTotalCount(int communitySerial) { int result =
	 * ccService.totalCountComments(communitySerial);
	 * System.out.println("communityserbvice commentservice totalcaoutn : " +
	 * result); return result; }
	 */
	/*
	 * @Override public boolean createComment(String usrId, int targetSerial, String
	 * contents, boolean isSecret) { ccService.createComment(usrId, targetSerial,
	 * contents, isSecret); return true; }
	 */
	/*
	 * @Override public boolean deleteComment(String usrId, int targetSerial) {
	 * ccService.deleteCommunity(usrId, targetSerial); return true; }
	 */
	/*
	 * @Override public ArrayList<Comment> ListCommentsAjax(int targetSerial, int
	 * pageNum){ ArrayList<Comment> list = ccService.ListCommunities(targetSerial,
	 * pageNum); return list; }
	 */
	/*
	 * @Override public boolean updateComment(int targetSerial, String contents,
	 * boolean isSecret) { ccService.updateComment(targetSerial, contents,
	 * isSecret); return true; }
	 * 
	 * 
	 */
}


