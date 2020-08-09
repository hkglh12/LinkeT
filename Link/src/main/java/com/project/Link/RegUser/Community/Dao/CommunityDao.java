package com.project.Link.RegUser.Community.Dao;

import java.sql.Timestamp;

import com.project.Link.Commons.Community.Dao.CommonsCommunityDao;


public interface CommunityDao extends CommonsCommunityDao{
	// 가장 마지막 게시글의 PK를 가져옴
	public int getLastSerial(String targetBoard, String prefix);
	// 게시글 작성
	public int createPosting(String targetBoard, String prefix, int serial, String usrId, String title, String contents, int fileCount,	Timestamp createDate, String subject);
	// 게시글 수정
	public int updateCommunity(int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate);
	// 게시글 삭제
	public int deleteCommunity(int targetSerial, Timestamp deleteDate);

}
