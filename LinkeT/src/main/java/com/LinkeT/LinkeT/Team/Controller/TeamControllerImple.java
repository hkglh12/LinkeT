package com.LinkeT.LinkeT.Team.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.Team.Dao.TeamDao.usrin;
import com.LinkeT.LinkeT.Team.Service.TeamService;
import com.LinkeT.LinkeT.Team.Service.TeamServiceImple;


@Controller
public class TeamControllerImple implements TeamController{
	//TODO Service실패하면 이게 imple가 아니어서일수도있음.
	@Autowired
	TeamServiceImple teamservice;

	public TeamControllerImple(){}
	
	@RequestMapping(value="/createteam", method=RequestMethod.POST)
	@Override
	public String teamCreate(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		System.out.println("TEAM CONTROLLER");
		System.out.println(request.getParameter("teamname"));
		String usrId = (String)session.getAttribute("sessionKey");
		String teamName = request.getParameter("teamname");
		System.out.println(usrId + " : " + teamName);
		int result = teamservice.createTeam(usrId, teamName);
		// 성공했다면, 아니라면
		return "success";
	}
	
	@RequestMapping(value="/getTeam", method=RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, String> teamGet(@RequestBody HashMap<String,String> target, Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> returnInfo = null;
		System.out.println(target);
		System.out.println(target.get("targetTeamCode"));
		Team team = null;
		team = teamservice.getTeam(target.get("targetTeamCode"));
		System.out.println(team);
		if(team!=null) {
			returnInfo = new HashMap<String, String>();
			returnInfo.put("teamCode", team.getTeamCode());
			returnInfo.put("teamOwner", team.getTeamOwner());
			returnInfo.put("teamName", team.getTeamName());
		}
	
		return returnInfo;
	}
	
}
