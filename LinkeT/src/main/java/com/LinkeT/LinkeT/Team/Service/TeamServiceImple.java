package com.LinkeT.LinkeT.Team.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.Team.Dao.TeamDao.usrin;
import com.LinkeT.LinkeT.Team.Dao.TeamDaoImple;

@Service
public class TeamServiceImple implements TeamService{
	
	@Autowired
	private TeamDaoImple teamdao;
	
	
	@Override
	public int createTeam(String usrId, String teamName) {
		int result = teamdao.createTeam(usrId, teamName);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public ArrayList<usrin> getTeam(String id) {
		// TODO Auto-generated method stub
		ArrayList<usrin> list = teamdao.getTeam(id);
		return list;
	}

}
