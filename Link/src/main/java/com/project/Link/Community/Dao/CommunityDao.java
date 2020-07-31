package com.project.Link.Community.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Community.Community;
import com.project.Link.Posting.Posting;
import com.project.Link.Posting.Dao.PostingDao;

public interface CommunityDao extends PostingDao {
	// 조회, 리스팅은 각각 다른 "객체"를 리턴함. 따라서 재선언
	// 조회
	public Community getCommunity(int targetSerial);
	// 리스팅
	public ArrayList<Community> getListCommunity(int page, int pagePerBlock);
	// Community는 noticement와는 다르게 "검색기능"이 추가되었음. 이에따라 검색대상이 주어졌을때 개수를 리턴하는 함수 생성
	public int getSearchCount(String targetBoard, String prefix, String searchCategory, String searchTarget);
	// 사용자는 자신이 남긴 게시글만 수정가능, 따라서 수정자 계정명이 필요하지 않음.
	// 관리자가 강제삭제하는경우 다른 속성명으로 저장함
	public int updateCommunity(int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate);
	// 사유 상 동
	public int deleteCommunity(int targetSerial, Timestamp deleteDate);
	public ArrayList<Community> searchListCommunity(int targetpage, int pageperBlock, String searchCategory, String searchTarget);
	/* public ArrayList<?> getListPosting(int page, int pagePerBlock); */
	/*
	 * public int getTotalCount(); public int create(String usrId, String cmtyTitle,
	 * String cmtyContents, int fileCount, Timestamp createDate); public
	 * ArrayList<Posting> list(int page); public Posting get(int targetSerial);
	 * public int update(int targetSerial, String cmtyTitle, String cmtyContents,
	 * int fileCount, Timestamp modifyDate); public int delete(int targetSerial,
	 * Timestamp deleteDate);
	 */
}
