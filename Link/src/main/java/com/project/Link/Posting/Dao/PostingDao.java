package com.project.Link.Posting.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Posting.Posting;
 
public interface PostingDao {
	
	// 동일하게 PostingDao를 구현할 것이므로 (Implements), 데이터베이스 테이블을 명시적으로 받아야 함.
	// 이는 서비스에서 구현
	// 전체 게시물 개수를 조회 (삭제한것 제외)
	public int getTotalCount(String targetBoard, String prefix);
	// 조회수 올려주는 메서드
	public int countUp(String targetBoard, String prefix, int targetSerial, int count);
	// 각각 연관된 Filetable에 연관게시글 serial을 연결하기 위해서 사용.
	// create 직후에 PK가 아닌값으로 DB를 재조회하는 행위를 막고자 사용. 
	public int getLastSerial(String targetBoard, String prefix);
	
	
	/* public ArrayList<?> getListPosting(int page, int pagePerBlock); */
	
	
	//**************************CRUD************************//
	// Posting 등록
	public int createPosting(String targetBoard, String prefix, int serial,String usrId,String title, String contents, int fileCount, Timestamp createDate);
	// Posting GET
	/* public Posting getPosting(int targetSerial); */
	// TODO Posting update(전체내용을 다시업로드, 파일이 변경될 경우를 생각해야함)
	//public int updatePosting(String targetBoard, String prefix, String usrId, int targetSerial,String title, String contents, int fileCount, Timestamp modifyDate);
	// Posting delete
	//public int deletePosting(String usrId, int targetSerial, Timestamp deleteDate);
	
	
	
	/*
	 * public int get(String targetBoard, String prefix, int serial); public int
	 * update(String targetBoard,String prefix,int count, int serial);
	 */
	
	//TODO postingDAO를 Implement하고,
	// 이걸 communitydao랑 noticementDao에서
	// extends 해서 만들어야함!
}
