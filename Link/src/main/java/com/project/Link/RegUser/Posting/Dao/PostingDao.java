package com.project.Link.RegUser.Posting.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.RegUser.Posting.Posting;
 
public interface PostingDao {
	
	// 동일하게 PostingDao를 구현할 것이므로 (Implements), 데이터베이스 테이블을 명시적으로 받아야 함.
	// 이는 서비스에서 구현
	// 전체 게시물 개수를 조회 (삭제한것 제외)
	// public int getTotalCount(String targetBoard, String prefix, String subject);
	// 조회수 올려주는 메서드
	public int countUp(String targetBoard, String prefix, int targetSerial, int count);

	
	
	/* public ArrayList<?> getListPosting(int page, int pagePerBlock); */
	
	
	
	/*
	 * public int get(String targetBoard, String prefix, int serial); public int
	 * update(String targetBoard,String prefix,int count, int serial);
	 */
	
	//TODO postingDAO를 Implement하고,
	// 이걸 communitydao랑 noticementDao에서
	// extends 해서 만들어야함!
}
