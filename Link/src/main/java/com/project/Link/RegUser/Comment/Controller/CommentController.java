package com.project.Link.RegUser.Comment.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.Comment.Comment;

public interface CommentController {
	public String userDirectListComments(Model model, HttpServletRequest request, HttpSession session);
	/* 자유게시판 댓글 작성 */
	public String PostComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);	
	/* 자유게시판 댓글 업데이트 */
	public String UpdateComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시판 댓글 삭제 */
	public String DeleteComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시판 개별리스팅*/
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session)  throws Exception;

}
