package com.project.Link.Noticement.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Posting.Posting;

public interface NoticementDao {
	
	public int total();
	/* 공지사항 생성 */
	public int create(String usrId, String ntcTitle, String ntcContents, int fileCount, Timestamp createDate);
	/* 공지사항 리스팅*/
	public ArrayList<Posting> list(int page, int pagePerBlock);
	/* 공지사항 조회*/
	public Posting get(int targetSerial);
	public int update(int targetSerial, String ntcTitle, String ntcContents, int fileCount, Timestamp modifyDate);
	public int delete(int targetSerial, Timestamp deleteDate);
}
