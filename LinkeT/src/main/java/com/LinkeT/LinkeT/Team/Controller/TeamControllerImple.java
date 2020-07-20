package com.LinkeT.LinkeT.Team.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.Team.Dao.TeamDao.usrin;
import com.LinkeT.LinkeT.Team.Service.TeamService;

@Controller
public class TeamControllerImple implements TeamController{
	//TODO Service실패하면 이게 imple가 아니어서일수도있음.
	private TeamService teamservice;
	
	@RequestMapping(value="/createteam", method=RequestMethod.POST)
	@Override
	public int teamCreate(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		String usrId = (String)session.getAttribute("sessionKey");
		String teamName = request.getParameter("teanName");
		int result = teamservice.createTeam(usrId, teamName);
		// 성공했다면, 아니라면
		return result;
	}
	@RequestMapping(value="/getTeam", method=RequestMethod.GET)
	@Override
	public String teamGet(Model model, HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		String usrId = (String)session.getAttribute("sessionKey");
		ArrayList<usrin> list = teamservice.getTeam(usrId);
		// 있다면 아니라면 해야함.
		return null;
	}

}
