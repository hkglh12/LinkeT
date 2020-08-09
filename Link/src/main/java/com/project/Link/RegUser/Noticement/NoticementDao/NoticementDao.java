package com.project.Link.RegUser.Noticement.NoticementDao;

import java.util.ArrayList;

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Posting.Dao.PostingDao;

public interface NoticementDao extends PostingDao {
	// 조회, 리스팅은 다른 "객체"가 리턴되므로 재선언
	// 조회
	public int getNoticementCount();
	public Noticement getNoticement(int targetSerial);
	// 리스팅
	public ArrayList<Noticement> getListNoticement(int page, int pagePerBlock);
}
