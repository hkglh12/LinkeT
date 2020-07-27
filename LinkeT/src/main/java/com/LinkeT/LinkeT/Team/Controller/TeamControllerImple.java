package com.LinkeT.LinkeT.Team.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import com.LinkeT.LinkeT.OrganizationChart.Service.OrganizationChartServiceImple;
import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.Team.Service.TeamService;
import com.LinkeT.LinkeT.Team.Service.TeamServiceImple;
import com.LinkeT.LinkeT.User.Controller.UserControllerImple;


@Controller
public class TeamControllerImple implements TeamController{
	//TODO Service실패하면 이게 imple가 아니어서일수도있음.
	private static final Logger logger = LoggerFactory.getLogger(TeamControllerImple.class);
	
	@Autowired
	TeamServiceImple tService;
	
	@Autowired
	OrganizationChartServiceImple oService;
	public TeamControllerImple(){}
	
	@RequestMapping(value="/createteam", method=RequestMethod.POST)
	@Override
	public String teamCreate(Model model, HttpServletRequest request, HttpSession session) {
		String result ="";
		// TODO Auto-generated method stub
		System.out.println("TEAM CONTROLLER");
		System.out.println(request.getParameter("t_name"));
		String usrId = (String)session.getAttribute("sessionKey");
		String teamName = request.getParameter("t_name");
		System.out.println(usrId + " : " + teamName);
		result = tService.createTeam(usrId, teamName);
		// 성공했다면, 아니라면
		if(result!="") {		//성공했다면
			Team targetTeam = tService.getTeam(result);
			int jResult = oService.joinTeam(usrId, result, "Owner", "TeamLeader");
			if(jResult>=1) {
				 model.addAttribute("contents", "create");
				 model.addAttribute("value","true");
				 model.addAttribute("teamCode", result);
				 return "success";
			}else {
				model.addAttribute("contents", "join");
				model.addAttribute("value","false");
				return "failed";
			}
			
			 
			 
		}else {		//실패했다면
			model.addAttribute("contents", "create");
			model.addAttribute("value", "false");
			return "failed";
		}
	}
	
	@RequestMapping(value="/getTeam", method=RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, String> teamGet(@RequestBody HashMap<String,String> target, Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> returnInfo = new HashMap<String, String>();
		System.out.println(target);
		System.out.println("t_code from request: " + target.get("t_code"));
		Team team = null;
		team = tService.getTeam(target.get("t_code"));
		System.out.println("Team result from query : " +team);
		if(team!=null) {
			returnInfo.put("teamCode", team.getTeamCode());
			returnInfo.put("teamOwner", team.getTeamOwner());
			returnInfo.put("teamName", team.getTeamName());
		}else {
			
			returnInfo.put("fstatus", "404");
		}
	
		return returnInfo;
	}
	//team 최초진입점이며, Dashboard를 제공합니다.
	@RequestMapping(value="/teammain", method=RequestMethod.POST)
	@Override
	public String teamMain(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		logger.info("/teammain called");
		String targetTeamCode =request.getParameter("teamcode");
		System.out.println(targetTeamCode);
		System.out.println("TeamMain for : " +session.getAttribute("sessionKey"));
		Team target = tService.getTeam(targetTeamCode);
		if(target == null) {
			model.addAttribute("contents","teammain");
			model.addAttribute("value", "failed");
			return "failed";
		}
		session.setAttribute("teamName", "tname");
		session.setAttribute("teamCode", "t_code");
		model.addAttribute("t_name", target.getTeamName());
		model.addAttribute("t_code" , target.getTeamCode());
		model.addAttribute("u_id", session.getAttribute("sessionKey"));
		model.addAttribute("t_owner", target.getTeamOwner());
		return "teammain";
	}
	
}
