package com.project.Link.RegUser.Comment.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Comment.Dao.CommonsCommentDao;

public interface CommentDao extends CommonsCommentDao{
	// 댓글 작성
	public int createComment(String usrId, int targetSerial, String contents, Timestamp createDate, boolean isSecret);
	// 댓글 수정
	public int updateComment(int serial,String contents, boolean isSecret, Timestamp modifyDate);
	// 댓글 삭제
	public int deleteComment(int serial,String usrId, Timestamp deleteDate);
	
	
}
