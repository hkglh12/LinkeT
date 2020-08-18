package com.project.Link.Commons.Community.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Comment.Service.CommonsCommentService;
import com.project.Link.Commons.Community.Community;
import com.project.Link.Commons.Community.Dao.CommonsCommunityDao;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("CommonsCommunityService")
public class CommonsCommunityServiceImple implements CommonsCommunityService{
	protected int pagePerBlock = 10;
	protected static final String targetBoard = "community";
	protected static final String targetBoardFile = targetBoard + "file";
	protected static final String cFilePath = "C:\\temp\\" + targetBoard + "\\";
	protected String prefix = "c_";
	
	@Autowired
	@Qualifier("CommonsCommunityDao")
	private CommonsCommunityDao cDao;
	
	@Autowired
	private UfileService ufService;
	
//	@Autowired
//	@Qualifier("CommonsCommentService")
//	private CommonsCommentService ccService;
	
	@Override
	public int directCountCommunities(HashMap<String, Object> params) {
		String baseSql = "select count(*) as count from community";
		
		if(!params.isEmpty()) baseSql += " where ";
		int i=0;
		for(Map.Entry<String, Object> et : params.entrySet()) {				
			if(et.getKey().equals("id")) {
				baseSql += "u_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}else {
				baseSql += "c_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}
			i++;
			if(i < params.size()) baseSql += " and ";
		}
		return cDao.directCountCommunities(baseSql);
	}
	
	@Override
	public int totalCountCommunities(String searchCategory, String searchTarget, String subject) {
		// 전체 게시글의 개수를 가져옵니다.
		if(searchCategory != null) {			//검색 대상이 있다면 DB 퀄럼에 맞게 변형
			if(searchCategory.equals("title")) {
				searchCategory = "c_"+searchCategory;
			}else if(searchCategory.equals("id")) {
				searchCategory = "u_"+searchCategory;
			}else {searchCategory = null;}
		}
		int totalCount = 0;
		if (searchTarget == null || searchCategory == null) {
			totalCount = cDao.getTotalCount(subject);
		} else {
			totalCount = cDao.getSearchCount(targetBoard, prefix, searchCategory, searchTarget, subject);
		}
		return totalCount;
	}

	@Override
	public ArrayList<Community> ListCommunities(int targetPage, String searchCategory, String searchTarget,
			String communitySubject) {
		// 게시글을 페이지단위로 DB로부터 추출합니다.
		if(searchCategory != null) {			//검색 대상이 있다면 DB 퀄럼에 맞게 변형
			if(searchCategory.equals("title")) {
				searchCategory = "c_"+searchCategory;
			}else if(searchCategory.equals("id")) {
				searchCategory = "u_"+searchCategory;
			}else {searchCategory = null;}
		}
		ArrayList<Community> list = null;
		if(!(communitySubject.equals("direct"))) {
			if (searchTarget == null || searchTarget == "") {
	
				// 검색 대상이 없을경우, 일반적인 추출을 시작합니다. (검색조건 없음)
				list = cDao.getListCommunity(targetPage, pagePerBlock, communitySubject);
			} else {
				// 검색 대상이 있을경우, 특정 타겟을 대상으로 추출합니다. (1차검색)
				list = cDao.searchListCommunity(targetPage, pagePerBlock, searchCategory, searchTarget, communitySubject);
			}
		}else {
			// 특정 유저에 대해 (1차) + 특정 조건으로 (2차) 검색합니다.
		//	list = cDao.directSerachUserCommunity(targetPage, pagePerBlock, searchCategory, searchTarget);
		}
//		list = ccService.totalCountComments(list);
		return list;
	}
	@Override
	public ArrayList<Community> DirectListCommunities(int targetPage, HashMap<String, Object> params) {
		ArrayList<Community> list = new ArrayList<Community>();
		String baseSql = "select * from community";
		
		if(!params.isEmpty()) baseSql += " where ";
		int i=0;
		for(Map.Entry<String, Object> et : params.entrySet()) {				
			if(et.getKey().equals("id")) {
				baseSql += "u_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}else {
				baseSql += "c_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}
			i++;
			if(i < params.size()) baseSql += " and ";
		}
		baseSql += " limit "+(targetPage * pagePerBlock)+", "+pagePerBlock;
		System.out.println(baseSql);
		list = cDao.directSerachUserCommunity(baseSql);
		return list;
	}
	@Override
	public Community getCommunity(int targetSerial) {
		Community targetCommunity = cDao.getCommunity(targetSerial);
		cDao.countUp(targetBoard, prefix, targetSerial, targetCommunity.getReadCount() + 1);
		targetCommunity.setuFileList(ufService.uFileGet(targetBoardFile, targetSerial));
		targetCommunity.setReadCount(targetCommunity.getReadCount() + 1);
		//targetCommunity.setComments(ccService.ListComments(targetSerial,0));
		return targetCommunity;
	}

//	@Override 
//	public int getCommentTotalCount(int communitySerial) {
//		// 총 게시글 댓글 개수를 가져옴
//		int result = ccService.totalCountComments(communitySerial);
//		System.out.println("communityserbvice commentservice totalcaoutn : " +result); return result;
//	}
	
//	 @Override 
//	 public ArrayList<Comment> ListCommentsAjax(int targetSerial, int pageNum){
//		 // 리스트를 Ajax통신
//		 ArrayList<Comment> list = ccService.ListComments(targetSerial,pageNum);
//		 return list; 
//	 }

	@Override
	public Community getLastUserCommunity(String usrId) {
		//특정 유저의 마지막 게시글을 가져옵니다
		Community result = cDao.getLastUserCommunity(usrId);
		if(result != null && result.getTitle().length() >= 6) {
			result.setTitle(result.getTitle().substring(0, 5)+"...");
		}
		return result;
	}



}
