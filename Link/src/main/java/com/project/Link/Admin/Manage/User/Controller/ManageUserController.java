package com.project.Link.Admin.Manage.User.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ManageUserController {
	// Controller를 extends하기엔 최초에 설정한 "사용자 / 관리자" 페이지의 분리를..감당할수없다..
	// 사용자 리스트를 호출
	public String getUserList(Model model, HttpServletRequest request, HttpSession session);
	// 사용자 강퇴
	public String banUser(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	// 사용자 정보 가져오기
	public String getUserDetail(Model model, HttpServletRequest request, HttpSession session);
}
