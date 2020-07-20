package com.LinkeT.LinkeT.User.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.LinkeT.LinkeT.User.User;
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
		boolean result = service.userRegister(usrId, usrPw, usrPhone, usrEmail,usrName);
		if(result == true) {
			return "signup_success";
		}else {
			return "signup_Failed";
		}
	}
	@RequestMapping(value="/usrLogin", method=RequestMethod.POST)
	@Override
	public String usrLogin(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("called");
		System.out.println(request);
		// TODO Auto-generated method stub
		String usrId = request.getParameter("usrinid");
		String usrPw = request.getParameter("usrinpw");
		System.out.println(usrId);
		System.out.println(usrPw);
		User result = service.loginUser(usrId, usrPw);
		if(result == null) {
			model.addAttribute("result", "failed");
			return "redirect:/resources/Login.html";
		}else {
			session.setAttribute("sessionKey", result.getUsrId());
			return "main";
		}
	}
	@RequestMapping(value="/me", method=RequestMethod.GET)
	@Override
	public String usrGet(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("TEST:*********************");
		System.out.println("param : " + request.getParameter("target"));
		System.out.println("TEST:SessionKey:" + session.getAttribute("sessionKey"));
		
		User result = service.getUser((String)session.getAttribute("sessionKey"));
		model.addAttribute("usrId", result.getUsrId());
		model.addAttribute("usrPw", result.getUsrPw());
		model.addAttribute("usrPhone", result.getUsrPhone());
		model.addAttribute("usrUsrEmail", result.getUsrEmail());
		model.addAttribute("usrTeam1", result.getTeam1());
		model.addAttribute("usrTeam2", result.getTeam2());
		model.addAttribute("usrTeam3", result.getTeam3());
		return "profile";
	}
	@Override
	public String LoginPageLoad() {
		// TODO Auto-generated method stub
		return null;
	}
	//TEST
	@RequestMapping(value="/main", method=RequestMethod.GET)
	@Override
	public String mainControl(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
