package com.LinkeT.LinkeT.User.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface UserController {
	public String LoginPageLoad();
	public String usrJoin(Model model, HttpServletRequest request);
	public String usrLogin(Model model, HttpServletRequest request);
}
