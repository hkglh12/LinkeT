package com.project.Link.Posting.Dao;

public interface PostingDao {
	public int get(String targetBoard, String prefix, int serial);
	public int update(String targetBoard,String prefix,int count, int serial);
}
