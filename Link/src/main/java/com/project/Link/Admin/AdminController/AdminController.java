package com.project.Link.Admin.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface AdminController {
	// 관리자에 대한 "직접"적인 작업은 로그인. 나머지는 "관리"라는 동작으로 표현
	public String LoginAdmin(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes);
}	
