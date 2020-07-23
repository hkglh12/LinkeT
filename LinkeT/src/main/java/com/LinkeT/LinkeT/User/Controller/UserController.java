package com.LinkeT.LinkeT.User.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
	
	
	public String LoginPageLoad();
	public HashMap<String,String> usrJoinValidatino(@RequestBody HashMap<String,String> target,HttpServletRequest request);
	public String usrJoin(Model model, HttpServletRequest request);
	public String usrLogin(Model model, HttpServletRequest request, HttpSession session);
	//get oneself
	public String usrGet(Model model, HttpServletRequest request, HttpSession session);
	//test
	public String usrTeamJoin(Model model, HttpServletRequest request, HttpSession session);
}
