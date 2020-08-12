package com.project.Link.RegUser.Community.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.Comment.Comment;

public interface CommunityController {
	//Session컨트롤 필요없음.
	/* public HashMap<String, String> sessionControl(HttpSession session); */
	/* 자유게시판 리스팅 */
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session);
	/* 자유게시판 작성 */
	public String PostCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception;
	/* 자유게시판 타게팅 get */
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시판 타게팅 update*/
	public String UpdateCommunity(Model model, MultipartHttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr)throws Exception;
	/* 자유게시판 타게팅 delete, 유저처리 해야함*/
	public String DeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시글에 등재된 파일 획득*/
	public void getCommunityFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response)throws Exception;
	/* 자유게시판 작성폼 획득*/
	public String getPostTemplate(Model model, HttpServletRequest request, HttpSession session);	
}
