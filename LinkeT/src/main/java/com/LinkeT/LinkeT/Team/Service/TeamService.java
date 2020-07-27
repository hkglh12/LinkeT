package com.LinkeT.LinkeT.Team.Service;

import java.util.ArrayList;

import com.LinkeT.LinkeT.Team.Team;

public interface TeamService {
	public String createTeam(String usrId, String teamName);
	public Team getTeam(String teamCode);
}
 