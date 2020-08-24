package com.project.Link.Commons.Community.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	
	public CommonsCommunityDao getcDao() {return cDao;}
	public void setcDao(CommonsCommunityDao cDao) {this.cDao = cDao;}
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}

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
	
	// 2차검색에 사용. (profile, userinfo)에서 "특정유저의 아이디" 로 검색 한 뒤에
	// 추가적으로 글 제목으로 검색할경우 개수 count
	// 한발 더 내딛어봄. Sql을 Service에서 만들어 내리는게 표준에서 많이 벗어난지는 알수가 없었음
	@Override
	public int directCountCommunities(HashMap<String, Object> params) {
		String baseSql = "select count(*) as count from community where c_deletedate is null";
		if(!params.isEmpty()) baseSql += " and ";
		int i=0;
		for(Map.Entry<String, Object> et : params.entrySet()) {				
			if(et.getKey().equals("id")) {
				baseSql += "u_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}else if(et.getKey().equals("deletedate")){
				baseSql += "c_"+et.getKey()+" "+et.getValue()+" ";
			}else {
				baseSql += "c_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}
			i++;
			if(i < params.size()) baseSql += " and ";
		}
		return cDao.directCountCommunities(baseSql);
	}
	
	//특정 게시글 Read
	@Override
	public Community getCommunity(int targetSerial) {
		Community targetCommunity = cDao.getCommunity(targetSerial);
		cDao.countUp(targetBoard, prefix, targetSerial, targetCommunity.getReadCount() + 1);
		targetCommunity.setuFileList(ufService.uFileGet(targetBoardFile, targetSerial));
		targetCommunity.setReadCount(targetCommunity.getReadCount() + 1);
		return targetCommunity;
	}
	//특정 유저의 마지막 게시글을 가져옵니다
	@Override
	public Community getLastUserCommunity(String usrId) {
		Community result = cDao.getLastUserCommunity(usrId);
		if(result != null && result.getTitle().length() >= 15) {
			result.setTitle(result.getTitle().substring(0, 15)+"...");
		}
		return result;
	}
	// 게시글 리스팅. 일반 (2차검색 없음)
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
		//if(!(communitySubject.equals("direct"))) {
			if (searchTarget == null || searchTarget == "") {
				// 검색 대상이 없을경우, 일반적인 추출을 시작합니다. (검색조건 없음)
				list = cDao.getListCommunity(targetPage, pagePerBlock, communitySubject);
			} else {
				// 검색 대상이 있을경우, 특정 타겟을 대상으로 추출합니다. (1차검색)
				list = cDao.searchListCommunity(targetPage, pagePerBlock, searchCategory, searchTarget, communitySubject);
			}
		return list;
	}
	// 게시글 리스팅, 2차검색 있음. (특정유저의 게시글 중 특정 제목 등..)
	@Override
	public ArrayList<Community> DirectListCommunities(int targetPage, HashMap<String, Object> params) {
		ArrayList<Community> list = new ArrayList<Community>();
		// 표준인지는..잘모르겠다..
		String baseSql = "select * from community where c_deletedate is null";
		if(!params.isEmpty()) baseSql += " and ";
		int i=0;
		for(Map.Entry<String, Object> et : params.entrySet()) {				
			if(et.getKey().equals("id")) {
				baseSql += "u_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}else if(et.getKey().equals("deletedate")){
				baseSql += "c_"+et.getKey()+" "+et.getValue()+" ";
			}else {
				baseSql += "c_"+et.getKey()+" like '%"+et.getValue()+"%' ";
			}
			i++;
			if(i < params.size()) baseSql += " and ";
		}
		baseSql += " limit "+(targetPage * pagePerBlock)+", "+pagePerBlock;
		list = cDao.directSerachUserCommunity(baseSql);
		return list;
	}
	@Override
	public boolean validateCommunityFile(String fileCode){
		String targetBoard = "communityfile";
		return ufService.uFileValidate(targetBoard, fileCode);
	}
}