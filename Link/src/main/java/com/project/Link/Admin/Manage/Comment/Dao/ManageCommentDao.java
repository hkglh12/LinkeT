package com.project.Link.Admin.Manage.Comment.Dao;

import java.sql.Timestamp;

import com.project.Link.Commons.Comment.Dao.CommonsCommentDao;

public interface ManageCommentDao extends CommonsCommentDao{
	// 댓글개수리턴, 전체게시글리턴, 댓글리스트리턴 Ajax대응포인트 등 Commons로 상속
	public boolean banComment(int targetSerial, String usrId, Timestamp deleteDate);
}
