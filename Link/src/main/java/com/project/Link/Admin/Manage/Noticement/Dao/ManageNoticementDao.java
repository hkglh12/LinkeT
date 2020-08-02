package com.project.Link.Admin.Manage.Noticement.Dao;

import java.sql.Timestamp;

import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDao;

public interface ManageNoticementDao extends NoticementDao{
	public int updateNoticement(String usrId,int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate);
	// 사유 상 동
	public int deleteNoticement(String usrId, int targetSerial, Timestamp deleteDate);
	public int createNoticement(String targetBoard, String prefix, int serial,String usrId,String title, String contents, int fileCount, Timestamp createDate);
}
