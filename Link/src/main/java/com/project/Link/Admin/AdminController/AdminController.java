package com.project.Link.Admin.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface AdminController {
	public String LoginAdmin(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes);
}	
