package com.project.Link.Comment.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Comment.Comment;

public interface CommentDao {
	 public int getTotalCount(int communitySerial); 
	 public int getUserCommentCount(String usrId);
	//댓글은 필요없음. 파일과 연계기능이 없으므로
	/* public int getLastSerial(); */
	public int createComment(String usrId, int targetSerial, String contents, Timestamp createDate, boolean isSecret);
	public ArrayList<Comment> getListComment(int communitySerial, int page, int pagePerBlock);
	public int updateComment(int serial,String contents, Timestamp modifyDate );
	public int deleteComment(int serial,String usrId, Timestamp deleteDate);
	
	
}
