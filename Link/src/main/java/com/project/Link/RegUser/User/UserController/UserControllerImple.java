package com.project.Link.RegUser.User.UserController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.HomeController;
import com.project.Link.RegUser.User.User;
import com.project.Link.RegUser.User.UserService.UserService;
import com.project.Link.SessionControl.SessionControl;

@Controller
@RequestMapping(value = "/usr/*")
public class UserControllerImple implements UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImple.class);

	@Autowired
	private UserService uService;
	@Autowired
	private SessionControl sc;

	public UserControllerImple() {};
	public UserService getuService() {return uService;}
	public SessionControl getSc() {return sc;}
	public void setSc(SessionControl sc) {this.sc = sc;}
	public void setuService(UserService uService) {this.uService = uService;}

	
	/* 데이터베이스에서 해당 객체의 존재 유무를 확인 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, String> usrJoinValidation(@RequestBody HashMap<String, String> target,
			HttpServletRequest request) {
		logger.info("/validate called");
		HashMap<String, String> returnInfo = new HashMap<String, String>();
		String key = "";
		for (String entrykey : target.keySet()) {
			key = entrykey;
		}
		String value = target.get(key);
		logger.info("Request for : " + key + " / value : " + value);
		String result = uService.userValidate(key, value);
		returnInfo.put("result", result);
		logger.info("answers : " + "result : " + result);
		return returnInfo;
	}


	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@Override
	public String RegisterUser(Model model, HttpServletRequest request, RedirectAttributes attributes) {
		logger.info("/usr/join called");

		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		String usrPhone = request.getParameter("u_phone");
		String usrEmail = request.getParameter("u_email");
		String usrName = request.getParameter("u_name");
		boolean result = uService.userRegist(usrId, usrPw, usrPhone, usrEmail, usrName);

		if (result == true) {
			// 성공 >> 성공 대상과 성공을 전달
			/* attributes.addFlashAttribute("result", "200"); */
			return "/User/user/joinSuccess";
		} else {
			// 실패 >> 실패 대상과 실패를 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "false");
			return "/User/failed";
		}
		
	}
	@RequestMapping(value="/login/form", method= RequestMethod.GET)
	public String GetLoginForm(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
		return "/User/user/login";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Override
	public String GetUser(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
		logger.info("/usrLogin Called");

		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		User result = uService.userGet(usrId, usrPw);
		if (result == null) {
			model.addAttribute("result", "false");
			return "/User/user/login";
		} else {
			boolean isAdmin = result.getUsrLevel() == 1 ? false : true;
			session.setAttribute("usrId", result.getUsrId());
			session.setAttribute("isAdmin", isAdmin);
			return "/User/main/main"; // 이거 메인컨트롤러로 다시 돌리자
		}
	}

	// 프로파일역할
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	@Override
	public String GetMe(Model model, HttpServletRequest request, HttpSession session) {
		User result = uService.userGet((String) session.getAttribute("usrId"));
		model.addAttribute("user", result);
		return "/User/user/profile";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public HashMap<String,String> UpdateUser(@RequestBody HashMap<String, String> target, Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> returnInfo = new HashMap<String, String>();
		String usrId = (String)session.getAttribute("usrId");
		String oldPw = target.get("old_pw");
		String newPw = target.get("new_pw");
		User result = uService.userGet(usrId, oldPw);
		if(result == null) {
			returnInfo.put("result","404");
		}else {
			boolean pwresult = uService.userUpdate(usrId, newPw);
			if(pwresult == true) {
				returnInfo.put("result", "200");
			}else {
				returnInfo.put("result", "undone");
			}
		}
		
		return returnInfo;
	}
	@ResponseBody
	@RequestMapping(value = "/signout", method = RequestMethod.POST)
	@Override
	public HashMap<String,String> DeleteUser(@RequestBody HashMap<String, String> target, Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> returnInfo = new HashMap<String, String>();
		String usrId = (String)session.getAttribute("usrId");
		String usrPw = target.get("u_pw");
		User result = uService.userGet(usrId, usrPw);
		if(result == null) {
			returnInfo.put("result","404");
		}else {
			System.out.println("delete start");
			boolean sOutResult = uService.userDelete(usrId);
			if(sOutResult == true) {
				returnInfo.put("result","200");
				session.invalidate();
			}else {
				returnInfo.put("result","500");
			}
		}
		return returnInfo;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@Override
	public void LogOut(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		try {
			response.sendRedirect("/Link/");
			session.invalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
