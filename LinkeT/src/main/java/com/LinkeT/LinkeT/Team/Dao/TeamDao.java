package com.LinkeT.LinkeT.Team.Dao;

import java.util.ArrayList;

import com.LinkeT.LinkeT.Team.Team;


public interface TeamDao {
	/*
	 * public class usrin{ private String teamname; private String usrgrade;
	 * 
	 * public String getTeamname() { return teamname; } public void
	 * setTeamname(String teamname) { this.teamname = teamname; } public String
	 * getUsrgrade() { return usrgrade; } public void setUsrgrade(String usrgrade) {
	 * this.usrgrade = usrgrade; } }
	 */
	
	public String createTeam(String usrId, String teamName);
	
	public Team getTeam(String teamCode);
	
}
