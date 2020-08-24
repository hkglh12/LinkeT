package com.project.Link.RegUser.Posting.Dao;

 
public interface PostingDao {
	
	// 조회수 올려주는 메서드
	public int countUp(String targetBoard, String prefix, int targetSerial, int count);
}
