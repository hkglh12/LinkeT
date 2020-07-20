package com.LinkeT.LinkeT.Team.Dao;

import java.util.ArrayList;


public interface TeamDao {
	public class usrin{
		private String teamname;
		private String usrgrade;
		
		public String getTeamname() {
			return teamname;
		}
		public void setTeamname(String teamname) {
			this.teamname = teamname;
		}
		public String getUsrgrade() {
			return usrgrade;
		}
		public void setUsrgrade(String usrgrade) {
			this.usrgrade = usrgrade;
		}
	}
	int createTeam(String usrId, String teamName);
	ArrayList<usrin> getTeam(String usrId);
	
}
