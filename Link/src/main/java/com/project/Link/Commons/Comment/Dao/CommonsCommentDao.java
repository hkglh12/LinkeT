package com.project.Link.Commons.Comment.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Commons.Comment.Comment;

public interface CommonsCommentDao {
	// 특정 게시글에 대한 총 댓글 개수 리턴
	public int getTotalCount(int communitySerial);
	// 특정 유저가 작성한 전체 게시글을 리턴
	public int getUserCommentCount(String usrId);
	// 댓글 리스트를 리턴
	public ArrayList<Comment> getListComment(int communitySerial, int page, int pagePerBlock);
	// 마지막 유저의 댓글을 리턴
	public Comment getLastUserComment(String usrId);
}
