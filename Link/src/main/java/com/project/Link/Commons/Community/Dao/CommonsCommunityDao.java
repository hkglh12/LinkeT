package com.project.Link.Commons.Community.Dao;

import java.util.ArrayList;

import com.project.Link.Commons.Community.Community;
import com.project.Link.RegUser.Posting.Dao.PostingDao;

public interface CommonsCommunityDao extends PostingDao {
	// 특정 게시판 게시글 총 개수를 가져옴
	public int getTotalCount(String subject);
	// 특정 유저가 작성한 개수를 가져옴 (User Profile과 Admin의 리스팅에서 사용)
	public int userCountCommunities(String usrId);
	// 특정 조건 하 검색 결과 개수를 가져옴
	public int getSearchCount(String targetBoard, String prefix, String searchCategory, String searchTarget, String subject);
	
	// 특정 게시글 조회
	public Community getCommunity(int targetSerial);
	// 게시글 리스팅
	public ArrayList<Community> getListCommunity(int page, int pagePerBlock, String communitySubject);
	// 특정 조건 하 검색결과 게시글 리스팅
	public ArrayList<Community> searchListCommunity(int targetpage, int pageperBlock, String searchCategory, String searchTarget, String communitySubject);
}
