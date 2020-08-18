package com.project.Link.RegUser.Community.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface CommunityController {
	/* 자유게시판 작성폼 획득*/
	public String getPostTemplate(Model model, HttpServletRequest request, HttpSession session);
	/* 자유게시판 작성 */
	public String PostCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception;
	/* 특정 자유게시글 타게팅 읽기 */
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 특정 게시글 업데이트*/
	public String UpdateCommunity(Model model, MultipartHttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr)throws Exception;
	/* 특정 게시글 삭제*/
	public String DeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	
	/* 자유게시판 리스팅 */
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session);
	/* 자유게시판 리스팅, 2차 검색 허용 (프로필에서 특정유저의- 특정 게시글 제목-으로 검색)*/
	public String DirectListCommunities(Model model, HttpServletRequest request, HttpSession session);
	
	/* 자유게시글에 등재된 파일 획득*/
	public void getCommunityFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response)throws Exception;
		
}
