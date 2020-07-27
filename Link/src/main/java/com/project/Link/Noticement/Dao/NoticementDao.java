package com.project.Link.Noticement.Dao;

import com.project.Link.Posting.Dao.PostingDao;

public interface NoticementDao extends PostingDao {
	
	// 특정 DB에 접속하는것이 명백하므로 데이터베이스 접두어와 타겟데이터베이스 선언

	
	// PostingDao가 공통기능만을 선언하였으므로, 그대로 상속받아서 사용
	/*
	 * public int total(); 공지사항 생성 public int create(String usrId, String ntcTitle,
	 * String ntcContents, int fileCount, Timestamp createDate); 공지사항 리스팅 public
	 * ArrayList<Posting> list(int page, int pagePerBlock); 공지사항 조회 public Posting
	 * get(int targetSerial); public int update(int targetSerial, String ntcTitle,
	 * String ntcContents, int fileCount, Timestamp modifyDate); public int
	 * delete(int targetSerial, Timestamp deleteDate);
	 */
}
