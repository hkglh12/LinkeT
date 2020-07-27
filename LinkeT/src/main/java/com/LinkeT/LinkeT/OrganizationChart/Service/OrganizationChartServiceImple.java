package com.LinkeT.LinkeT.OrganizationChart.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LinkeT.LinkeT.OrganizationChart.OrganizationChart;
import com.LinkeT.LinkeT.OrganizationChart.Dao.OrganizationChartDao;
import com.LinkeT.LinkeT.User.User;

@Service
public class OrganizationChartServiceImple implements OrganizationChartService {
	
	@Autowired
	OrganizationChartDao dao;
	
	@Override
	public int joinTeam(String usrId, String teamCode, String usrGrade, String usrPart) {
		int result = 0;
		Timestamp usrJoinDate = Timestamp.valueOf(LocalDateTime.now());
		String ownerShip = "";
		result = dao.joinTeam(usrId, teamCode, usrGrade, usrPart, usrJoinDate);
		return result;
	}

	@Override
	public ArrayList<OrganizationChart> getUsrBelong(String usrId) {
		ArrayList<OrganizationChart> resultset = dao.getUsrBelong(usrId);
		return resultset;
	}

	@Override
	public ArrayList<User> getTeamUsrs(String teamCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
