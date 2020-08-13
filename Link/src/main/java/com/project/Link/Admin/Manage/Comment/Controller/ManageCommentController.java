package com.project.Link.Admin.Manage.Comment.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.Comment.Comment;

public interface ManageCommentController {
	/* 자유게시판 개별리스팅*/
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session)  throws Exception;
	// 댓글 Ban
	public String BanComment(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr);
	// 특정 유저가 작성한 댓글을 페이징 기법으로 전달
	public String getUserDirectComments(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr);
}
