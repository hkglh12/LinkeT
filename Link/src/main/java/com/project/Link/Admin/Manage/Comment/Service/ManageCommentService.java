package com.project.Link.Admin.Manage.Comment.Service;

import java.sql.Timestamp;

import com.project.Link.Commons.Comment.Service.CommonsCommentService;

public interface ManageCommentService extends CommonsCommentService{
	// 댓글 Ban
	public boolean banComment(int targetSerial, String usrId);
}
