package com.project.Link.UserController;

import java.util.ArrayList;
import java.util.HashMap;

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

import com.project.Link.HomeController;
import com.project.Link.SessionControl.SessionControl;
import com.project.Link.User.User;
import com.project.Link.UserService.UserService;

@Controller
@RequestMapping(value = "/usr/*")
public class UserControllerImple implements UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImple.class);

	@Autowired
	private UserService uService;
	@Autowired
	private SessionControl sc;

	/*
	 * @Autowired OrganizationChartServiceImple oService;
	 * 
	 * @Autowired TeamServiceImple tService;
	 */
	public UserControllerImple() {
	};

	public UserService getuService() {
		return uService;
	}

	public SessionControl getSc() {
		return sc;
	}

	public void setSc(SessionControl sc) {
		this.sc = sc;
	}

	public void setuService(UserService uService) {
		this.uService = uService;
	}

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

	/*
	 * 메인컨트롤러로 뺴자
	 * 
	 * @RequestMapping(value="/main", method=RequestMethod.POST)
	 * 
	 * @Override public String goMain(Model model, HttpServletRequest request,
	 * HttpSession session) {
	 * 
	 * 일반 form post방식은 이것. Jquery에서 JSON통신방식의 가장 좋은 예는 "TeamController > createTeam"
	 * // TODO Auto-generated method stub logger.info("/goMainCalled"); String sky =
	 * ""; sky = (String)session.getAttribute("sessionKey");
	 * System.out.println(sky); if(sky!=null) { return "main"; }else { return
	 * "login"; } }
	 */

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@Override
	public String RegisterUser(Model model, HttpServletRequest request) {
		logger.info("/usr/join called");

		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		String usrPhone = request.getParameter("u_phone");
		String usrEmail = request.getParameter("u_email");
		String usrName = request.getParameter("u_name");
		boolean result = uService.userRegist(usrId, usrPw, usrPhone, usrEmail, usrName);

		if (result == true) {
			// 성공 >> 성공 대상과 성공을 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "true");
			return "success";
		} else {
			// 실패 >> 실패 대상과 실패를 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "false");
			return "failed";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Override
	public String GetUser(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("/usrLogin Called");

		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		User result = uService.userGet(usrId, usrPw);
		boolean isAdmin = result.getUsrLevel() == 1 ? false : true;
		if (result == null) {
			model.addAttribute("result", "failed");
			return "login";
		} else {
			session.setAttribute("usrId", result.getUsrId());
			session.setAttribute("isAdmin", isAdmin);
			logger.info("Login Succeed!");
			return "main"; // 이거 메인컨트롤러로 다시 돌리자
		}
	}

	// 프로파일역할
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	@Override
	public String GetMe(Model model, HttpServletRequest request, HttpSession session) {

		/* HashMap<String,String> sessionExtract = sc.sessionControl(session); */
		/* if(sessionExtract.get("usrId")==null) { */
		/*
		 * if (session.getAttribute("usrId") == null) { model.addAttribute("result",
		 * "sout"); return "login"; }
		 */
		User result = uService.userGet((String) session.getAttribute("usrId"));
		model.addAttribute("user", result);
		/*
		 * model.addAttribute("usrId", result.getUsrId()); model.addAttribute("usrPw",
		 * result.getUsrPw()); model.addAttribute("usrName", result.getUsrName());
		 * model.addAttribute("usrPhone", result.getUsrPhone());
		 * model.addAttribute("usrEmail", result.getUsrEmail());
		 * model.addAttribute("usrLevel", result.getUsrLevel());
		 * model.addAttribute("usrSignInDate", result.getUsrIndate());
		 */
		/* 나중에 카운터만들어서 댓글, 게시글 개수 새주자 */
		return "profile";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public String UpdateUser(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/signout", method = RequestMethod.POST)
	@Override
	public String DeleteUser(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@Override
	public String LogOut(Model model, HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return "login";
	}

}
