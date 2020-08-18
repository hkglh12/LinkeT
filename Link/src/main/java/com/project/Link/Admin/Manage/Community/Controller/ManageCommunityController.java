package com.project.Link.Admin.Manage.Community.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ManageCommunityController{
	// 단일 게시글 Get (Read)
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 게시글 리스팅
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session);
	// 특정 유저의 게시판 주제 관계없이 게시글 리스팅
	public String DirectListCommunities(Model model, HttpServletRequest request, HttpSession session);
	
	// 게시글 Ban
	public String BanCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 게시글 대량Ban
	public String BulkBanCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 파일 다운로드
	public void getCommunityFile(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception;
	
}
