package com.project.Link.RegUser.Noticement.NoticementDao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Posting.Dao.PostingDao;
import com.project.Link.RegUser.Noticement.Noticement;

public interface NoticementDao extends PostingDao {
	// 조회, 리스팅은 다른 "객체"가 리턴되므로 재선언
	// 조회
	public int getNoticementCount(String targetBoard, String prefix);
	public Noticement getNoticement(int targetSerial);
	// 리스팅
	public ArrayList<Noticement> getListNoticement(int page, int pagePerBlock);
	
	//update, delete가 각각 Noticement와 Community가 가지는 속성이 다르므로 재생성
	// 공지사항은 한 관리자가 다른 관리자의 공지사항을 수정할 수 있음.
	// 이때, 수정한사람이 최종 작성자로 변경됨.

}
