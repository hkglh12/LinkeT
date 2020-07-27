package com.LinkeT.LinkeT.User.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.LinkeT.LinkeT.OrganizationChart.OrganizationChart;
import com.LinkeT.LinkeT.OrganizationChart.Service.OrganizationChartServiceImple;
import com.LinkeT.LinkeT.Team.Service.TeamServiceImple;
import com.LinkeT.LinkeT.User.User;
import com.LinkeT.LinkeT.User.Service.UserService;
import com.LinkeT.LinkeT.User.Service.UserServiceImple;

@Controller
public class UserControllerImple implements UserController{
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImple.class);
	
	@Autowired
	UserServiceImple uService;
	@Autowired
	OrganizationChartServiceImple oService; 
	@Autowired
	TeamServiceImple tService;
	
	
	public UserControllerImple() {}
	
	public UserServiceImple getuService() {
		return uService;
	}


	public void setuService(UserServiceImple uService) {
		this.uService = uService;
	}


	public OrganizationChartServiceImple getoService() {
		return oService;
	}


	public void setoService(OrganizationChartServiceImple oService) {
		this.oService = oService;
	}


	public TeamServiceImple gettService() {
		return tService;
	}


	public void settService(TeamServiceImple tService) {
		this.tService = tService;
	}

	public UserServiceImple getService() {
		return uService;
	}

	public void setService(UserServiceImple service) {
		this.uService = service;
	}	

	//회원가입시에 "아이디, 이메일, 팀코드"가 유효한지 확인합니다. 단, 팀코드는 true/false가 뒤집혀서 전달되는데, 이는 프론트에서 처리합니다.
		@RequestMapping(value="/validate", method=RequestMethod.POST)
		@Override
		@ResponseBody
		public HashMap<String, String> usrJoinValidation(@RequestBody HashMap<String,String> target, HttpServletRequest request) {
			logger.info("/validate called");
			HashMap<String, String> returnInfo=new HashMap<String,String>();
			String key = "";
			for(String entrykey : target.keySet()) {
				key = entrykey;
			}
			String value = target.get(key);
			logger.info("Request for : " + key + " / value : " +value);
			String result = uService.userValidator(key,value);
			returnInfo.put("result", result);
			logger.info("answers : " + "result : " + result);
			return returnInfo;
		}
		
	//사용자는 로그인합니다.
		@RequestMapping(value="/usrLogin", method=RequestMethod.POST)
		@Override
		public String usrLogin(Model model, HttpServletRequest request, HttpSession session) {
		
			/* 일반 form post방식은 이것. Jquery에서 JSON통신방식의 가장 좋은 예는 "TeamController > createTeam" */ 
			// TODO Auto-generated method stub
			logger.info("/usrLogin Called");
			
			String usrId = request.getParameter("u_id");
			String usrPw = request.getParameter("u_pw");
			logger.info("User id" + usrId +" tried to login");
			
			User result = uService.loginUser(usrId, usrPw);
			
			if(result == null) {
				model.addAttribute("result", "failed");
				return "login";
			}else{
				session.setAttribute("sessionKey", result.getUsrId());
				System.out.println(result.getUsrId());
				return "main";
			}
		}
		
	//사용자 회원가입을 처리합니다
	//회원가입시에 팀코드 입력이 성공했다면 팀 가입도 이곳에서 처리합니다.
	@RequestMapping(value="/usrJoin", method=RequestMethod.POST)
	@Override
	public String usrJoin(Model model, HttpServletRequest request) {
		logger.info("/usrJoin Called");
		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		String usrPhone = request.getParameter("u_phone");
		String usrEmail = request.getParameter("u_email");
		String usrName = request.getParameter("u_name");

		logger.info(usrId + " : " + usrPw + " : " + usrPhone + " : " + usrEmail + " : " + usrName);
		
		boolean result = uService.userRegister(usrId, usrPw, usrPhone, usrEmail,usrName);
		
		if(result == true) {
			//성공 >> 성공 대상과 성공을 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "true");
			return "success";
		}else {
			//실패 >> 실패 대상과 실패를 전달
			model.addAttribute("contents", "join");
			model.addAttribute("value", "false");
			return "failed";
		}
	}
	
	
	// 메인으로 돌려주는 역할을 합니다. 이때 세션의 여부를 확인하여 세션이있다면(로그인기록이있다면) main으로, 없다면 Login창으로 돌려보냅니다.
	@RequestMapping(value="/usrmain", method=RequestMethod.GET)
	@Override
	public String usrTurnMain(Model model, HttpServletRequest request, HttpSession session) {
	
		/* 일반 form post방식은 이것. Jquery에서 JSON통신방식의 가장 좋은 예는 "TeamController > createTeam" */ 
		// TODO Auto-generated method stub
		logger.info("/usrLogin Called");
		String sky = "";
		sky = (String)session.getAttribute("sessionKey");
		System.out.println(sky);
		if(sky!=null) {
			return "main";
		}else {
			return "login";
			}
		}
	// mypage(profile)역할을 합니다.
	@RequestMapping(value="/me", method=RequestMethod.GET)
	@Override
	public String usrGet(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		// session이 없다면 로그인으로 되돌려보냄
		if((String)session.getAttribute("sessionKey")==null) {
			model.addAttribute("result", "sessionOut");
			return "login";
		}
		User result = uService.getUser((String)session.getAttribute("sessionKey"));
		model.addAttribute("usrId", result.getUsrId());
		model.addAttribute("usrPw", result.getUsrPw());
		model.addAttribute("usrPhone", result.getUsrPhone());
		model.addAttribute("usrEmail", result.getUsrEmail());
		model.addAttribute("usrteamcount",result.getUsrTeamcount());
		model.addAttribute("usrSignInDate", result.getSignindate());
		
		ArrayList<OrganizationChart> teamlist = oService.getUsrBelong(result.getUsrId());
		System.out.println(teamlist);
		
		for(int i=0; i<3; i++) {
			if(i<teamlist.size()) {
				model.addAttribute("usrTeam"+(i+1), tService.getTeam(teamlist.get(i).getTeamCode()).getTeamName());
				model.addAttribute("usrTeam"+(i+1)+"code",teamlist.get(i).getTeamCode());
			}else {
				model.addAttribute("usrTeam"+(i+1), "");
				model.addAttribute("usrTeam"+(i+1)+"code","");
			}
		}

		return "profile";
	}
	//사용자는 팀에 가입할 수 있습니다. 이때 userservice, organizationchartservice를 사용합니다. (컨트롤러에서 타 서비스 참조)
	//팀에 가입했다면 org chart에 추가해주고, userteamcount를 업데이트해야합니다.
	@RequestMapping(value="/teamJoin", method=RequestMethod.POST)
	@Override
	public String usrTeamJoin(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		logger.info("/teamJoin called");
		//TODO :: 사용자를 추가하면 동시에 organization 추가항목도 짜야한다.
		int result = 0;
		
		String teamCode = request.getParameter("t_code");
		String teamName = request.getParameter("t_name");
		String teamOwner = request.getParameter("t_owner");
		String usrId = request.getParameter("u_id");
		String usrGrade = request.getParameter("u_grade");
		String usrPart = request.getParameter("u_part");
		
		logger.info("TeamJoin :: // "+teamCode + " : " + teamName + " : " + teamOwner + " : " + usrId + " : " +usrGrade + " : " + usrPart);
		result = oService.joinTeam(usrId, teamCode, usrGrade, usrPart);
		if(result!=0) {	// join succeed
			result = uService.userTeamCountUpdate(teamCode, usrId, usrGrade, usrPart);
			return "success";
		}else {
			result = 0; // == failed
			return "failed";
		}
		
		
	}

	



}
