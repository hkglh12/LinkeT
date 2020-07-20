package com.LinkeT.LinkeT.User.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface UserController {
	
	
	public String LoginPageLoad();
	public String usrJoin(Model model, HttpServletRequest request);
	public String usrLogin(Model model, HttpServletRequest request, HttpSession session);
	//get oneself
	public String usrGet(Model model, HttpServletRequest request, HttpSession session);
	//test
	public String mainControl(Model model, HttpServletRequest request, HttpSession session);
}
