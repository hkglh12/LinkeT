package com.project.Link.Admin.Manage.Community.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.RegUser.Community.Controller.CommunityController;

public interface ManageCommunityController{
	// 게시글 로드
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 게시글 리스팅
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session);
	// 게시글 Ban
	public String BanCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 게시글 대량Ban
	public String BulkDeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 댓글 Ban
	public String BanComment(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr);
	// 파일 다운로드
	public void getCommunityFile(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception;
	/* 자유게시판 개별리스팅*/
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session)  throws Exception;
}
