package com.LinkeT.LinkeT.OrganizationChart.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.LinkeT.LinkeT.OrganizationChart.OrganizationChart;
import com.LinkeT.LinkeT.User.User;

public interface OrganizationChartDao {
	public int joinTeam(String usrId, String teamCode, String usrGrade, String usrPart, Timestamp usrJoinDate);
	public ArrayList<OrganizationChart> getUsrBelong(String usrId);
	public ArrayList<User> getTeamUsrs(String teamCode);
}
