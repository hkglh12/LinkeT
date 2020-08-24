package com.project.Link.RegUser.User.UserController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.User.User;
import com.project.Link.RegUser.Comment.Service.CommentService;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.RegUser.User.UserService.UserService;
import com.project.Link.Ufile.Service.UfileService;

@Controller
@RequestMapping(value = "/usr/*")
public class UserControllerImple implements UserController {
	

	@Autowired
	@Qualifier("UserService")
	private UserService uService;
	@Autowired
	@Qualifier("UserCommunityService")
	private CommunityService cService;
	@Autowired
	@Qualifier("UserCommentService")
	private CommentService ccService;
	@Autowired
	private UfileService ufService;
	
	public UserControllerImple() {};
	public UserService getuService() {return uService;}
	public void setuService(UserService uService) {this.uService = uService;}

	
	/* 데이터베이스에서 이미 중복된 내용이 있는지 확인*/
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, String> usrJoinValidation(@RequestBody HashMap<String, String> target,HttpServletRequest request) {
		
		HashMap<String, String> returnInfo = new HashMap<String, String>();
		String key = "";
		for (String entrykey : target.keySet()) {
			key = entrykey;
		}
		String value = target.get(key);
		String result = uService.userValidate(key, value);
		
		returnInfo.put("result", result);
		return returnInfo;
	}
	// 회원가입 요청
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@Override
	public String RegisterUser(Model model, HttpServletRequest request, RedirectAttributes attributes) {
		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		String usrPhone = request.getParameter("u_phone");
		String usrEmail = request.getParameter("u_email");
		String usrName = request.getParameter("u_name");
		
		boolean result = uService.userRegist(usrId, usrPw, usrPhone, usrEmail, usrName);
		if (result == true) {
			return "/User/user/joinSuccess";
		} else {
			model.addAttribute("contents", "join");
			model.addAttribute("value", "false");
			return "/User/failed";
		}
	}
	// 로그인 창으로 이동
	@RequestMapping(value="/login/form", method= RequestMethod.GET)
	public String GetLoginForm(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
		return "/User/user/login";
	}
	//로그인 요청
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Override
	public String GetUser(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
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
			return "/User/main/main";
		}
	}

	// 프로파일역할
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	@Override
	public String GetMe(Model model, HttpServletRequest request, HttpSession session) {
		User result = uService.getUserDetail((String) session.getAttribute("usrId"));
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", result.getUsrId());
		params.put("deletedate", "is null");
		result.setCommentCount(ccService.getUserCommentsCount(result.getUsrId()));
		result.setCommunityCount(cService.directCountCommunities(params));
		params.remove("deletedate");
		result.setFileCount(ufService.getUserFileCount(result.getUsrId()));
		model.addAttribute("user", result);
		return "/User/user/profile";
	}
	// 비밀번호 변경에 응답
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
	//회원탈퇴에 응답
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
	//로그아웃 (session 연결 해제)
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
