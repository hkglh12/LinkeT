package com.project.Link.Admin.Manage.Noticement.Dao;

import java.sql.Timestamp;

import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDao;

public interface ManageNoticementDao extends NoticementDao{
	// total count, read, listing은 user단 noticementdao와 동일하게 동작하므로, 상속으로만 처리합니다.
	// 공지사항 업데이트
	public int updateNoticement(String usrId,int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate);
	// 공지사항 삭제
	public int deleteNoticement(String usrId, int targetSerial, Timestamp deleteDate);
	// 공지사항 생성
	public int createNoticement(String targetBoard, String prefix, int serial,String usrId,String title, String contents, int fileCount, Timestamp createDate);
	/* 가장 마지막 게시글의 serial 넘버를 가져옴*/
	public int getLastSerial(String targetBoard, String prefix);
}
