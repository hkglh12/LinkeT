package com.project.Link.Admin.Manage.Noticement.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.RegUser.Noticement.NoticementController.NoticementController;

public interface ManageNoticementController extends NoticementController{
	/* 해당 클래스 관리자로 승격해야할껄?>*/
	public String getPostTemplate(Model model, HttpServletRequest request, HttpSession session);
	/* 공지사항작성 */
	public String PostNoticement(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception;
	/* 공지사항삭제 */
	public String DeleteNoticement(Model model, HttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr);
	/* 공지사항업데이트 */
	public String UpdateNoticement(Model model, MultipartHttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr)throws Exception;
}
