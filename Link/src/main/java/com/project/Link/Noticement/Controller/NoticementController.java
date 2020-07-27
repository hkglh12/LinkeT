package com.project.Link.Noticement.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface NoticementController {
	/* 공지사항리스팅 */
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr);
	/* 공지사항작성 */
	public String PostNoticement(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception;	
	/* 공지사항조회 */
	public String GetNoticement(Model model, HttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr);
	/* 공지사항업데이트 */
	public String UpdateNoticement(Model model, MultipartHttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr)throws Exception;
	/* 공지사항삭제 */
	public String DeleteNoticement(Model model, HttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr);
}
