package com.LinkeT.LinkeT.User.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LinkeT.LinkeT.HomeController;
import com.LinkeT.LinkeT.User.User;
import com.LinkeT.LinkeT.User.Service.UserService;
import com.LinkeT.LinkeT.User.Service.UserServiceImple;

@Controller
public class UserControllerImple implements UserController{
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImple.class);
	
	@Autowired
	UserServiceImple service;
	
	public UserControllerImple() {}
	
	//Join Validator.
	@RequestMapping(value="/validate", method=RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, String> usrJoinValidatino(@RequestBody HashMap<String,String> target, HttpServletRequest request) {
		logger.info("/validate called");
		HashMap<String, String> returnInfo=new HashMap<String,String>();
		String key = "";
		for(String entrykey : target.keySet()) {
			key = entrykey;
		}
		String value = target.get(key);
		logger.info("Request for : " + key + " / value : " +value);
		String result = service.userValidator(key,value);
		returnInfo.put("result", result);
		logger.info("answers : " + "result : " + result);
		return returnInfo;
	}
	
	@RequestMapping(value="/usrJoin", method=RequestMethod.POST)
	@Override
	public String usrJoin(Model model, HttpServletRequest request) {
		// 사용자 회원가입 control
		logger.info("/usrJoin Called");
		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		String usrPhone = request.getParameter("u_phone");
		String usrEmail = request.getParameter("u_email");
		String usrName = request.getParameter("u_name");
		logger.info(usrId + " : " + usrPw + " : " + usrPhone + " : " + usrEmail + " : " + usrName);
		
		//사용자 서비스 호출
		boolean result = service.userRegister(usrId, usrPw, usrPhone, usrEmail,usrName);
		
		if(result == true) {
			//성공 >> 성공 대상과 성공을 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "true");
			return "success";
		}else {
			//실패 >> 실패 대상과 실패를 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "fail");
			return "signup_Failed";
		}
	}
	@RequestMapping(value="/usrLogin", method=RequestMethod.POST)
	@Override
	public String usrLogin(Model model, HttpServletRequest request, HttpSession session) {
	
		/* 일반 form post방식은 이것. Jquery에서 JSON통신방식의 가장 좋은 예는 "TeamController > createTeam" */ 
		// TODO Auto-generated method stub
		logger.info("/usrLogin Called");
		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		logger.info("User id" + usrId +" tried to login");
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
		
		User result = service.getUser((String)session.getAttribute("sessionKey"));
		System.out.println("/me");
		System.out.println(result);
		System.out.println(result.getUsrId());
		System.out.println(result.getUsrName());
		System.out.println(result.getUsrPw());
		System.out.println(result.getUsrEmail());
		model.addAttribute("usrId", result.getUsrId());
		model.addAttribute("usrPw", result.getUsrPw());
		model.addAttribute("usrPhone", result.getUsrPhone());
		model.addAttribute("usrEmail", result.getUsrEmail());
		model.addAttribute("usrteamcount",result.getUsrTeamcount());
		return "profile";
	}
	@RequestMapping(value="/teamJoin", method=RequestMethod.POST)
	@Override
	public String usrTeamJoin(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("/teamJoin called");
		//TODO :: 사용자를 추가하면 동시에 organization 추가항목도 짜야한다.
		String teamCode = request.getParameter("t_code");
		String teamName = request.getParameter("t_name");
		String teamOwner = request.getParameter("t_owner");
		String usrId = request.getParameter("u_id");
		String usrGrade = request.getParameter("u_grade");
		String workpart = request.getParameter("u_part");
		
		System.out.println(teamCode + " : " + teamName + " : " + teamOwner + " : " + usrId + " : " +usrGrade + " : " + workpart);
		usrId = "test";
		int result = service.joinTeamUser(usrId, teamCode);
		return null;
	}
	@Override
	public String LoginPageLoad() {
		// TODO Auto-generated method stub
		return null;
	}
	//TEST


	public UserServiceImple getService() {
		return service;
	}

	public void setService(UserServiceImple service) {
		this.service = service;
	}

	

	



}
