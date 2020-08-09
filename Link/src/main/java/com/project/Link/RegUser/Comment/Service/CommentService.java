package com.project.Link.RegUser.Comment.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Comment.Service.CommonsCommentService;
import com.project.Link.Commons.Community.Community;

public interface CommentService extends CommonsCommentService {
	// 댓글 작성
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret);
	// 댓글 수정
	public boolean updateComment(int targetSerial, String contents, boolean isSecret);
	// 댓글 삭제
	public boolean deleteComment(String usrId, int targetSerial);
}
