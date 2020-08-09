package com.project.Link.Admin.Manage.Comment.Dao;

import java.sql.Timestamp;

import com.project.Link.Commons.Comment.Dao.CommonsCommentDao;

public interface ManageCommentDao extends CommonsCommentDao{
	public boolean banComment(int targetSerial, String usrId, Timestamp deleteDate);
}
