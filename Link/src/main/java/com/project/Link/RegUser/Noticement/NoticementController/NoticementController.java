package com.project.Link.RegUser.Noticement.NoticementController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface NoticementController {
	/* 공지사항리스팅 */
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr);
	/* 공지사항조회 */
	public String GetNoticement(Model model, HttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr);

	/* 공지사항에 게시된 파일을 획득합니다 */
	public void getNoticementFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response)throws Exception;
}
