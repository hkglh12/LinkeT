package com.LinkeT.LinkeT.Team.Service;

import java.util.ArrayList;

import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.Team.Dao.TeamDao.usrin;

public interface TeamService {
	public int createTeam(String usrId, String teamName);
	public Team getTeam(String teamCode);
}
 