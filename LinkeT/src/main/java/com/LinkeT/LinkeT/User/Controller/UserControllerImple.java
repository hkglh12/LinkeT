package com.LinkeT.LinkeT.User.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.LinkeT.LinkeT.User.Service.UserService;
import com.LinkeT.LinkeT.User.Service.UserServiceImple;

@Controller
public class UserControllerImple implements UserController{
	
	@Autowired
	UserServiceImple service;
	
	public UserControllerImple() {}
	
	@RequestMapping(value="/usrJoin", method=RequestMethod.POST)
	@Override
	public String usrJoin(Model model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("RegisterCalled");
		String usrId = request.getParameter("id");
		String usrPw = request.getParameter("pw");
		String usrPhone = request.getParameter("phonemiddle");
		String usrEmail = request.getParameter("email");
		String usrName = request.getParameter("usrname");
		service.userRegister(usrId, usrPw, usrPhone, usrEmail,usrName);
		return null;
	}
	@RequestMapping(value="/usrLogin", method=RequestMethod.POST)
	@Override
	public String usrLogin(Model model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
