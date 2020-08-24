package com.project.Link.Admin.Manage.Comment.Service;


import com.project.Link.Commons.Comment.Service.CommonsCommentService;

public interface ManageCommentService extends CommonsCommentService{
	// Commons로부터 Ajax Func, 조건하 댓글카운트, 전체카운트
	// get comments 리턴받는것은 상속으로 (공통기능)
	// 댓글 Ban
	public boolean banComment(int targetSerial, String usrId);
}
