package com.LinkeT.LinkeT.Team.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.Team.Dao.TeamDao.usrin;

// teamlist는 별도로 관리할것

public interface TeamController {
	public String teamCreate(Model model, HttpServletRequest request, HttpSession session);
	public HashMap<String, String> teamGet(@RequestBody HashMap<String, String> target,Model model, HttpServletRequest request, HttpSession session);

}
