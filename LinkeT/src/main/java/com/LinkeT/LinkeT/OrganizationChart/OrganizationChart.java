package com.LinkeT.LinkeT.OrganizationChart;

import java.sql.Timestamp;

public class OrganizationChart {
	private int organizationChart_serial;
	private String teamCode;
	private String usrId;
	private String usrGrade;
	private String usrPart;
	private Timestamp usrJoinDate;
	private Timestamp usrOutDate;
	
	public Timestamp getUsrJoinDate() {
		return usrJoinDate;
	}
	public void setUsrJoinDate(Timestamp u_joindate) {
		this.usrJoinDate = u_joindate;
	}
	public Timestamp getUsrOutDate() {
		return usrOutDate;
	}
	public void setUsrOutDate(Timestamp u_outdate) {
		this.usrOutDate = u_outdate;
	}
	public int getOrganizationChart_serial() {
		return organizationChart_serial;
	}
	public void setOrganizationChart_serial(int organizationChart_serial) {
		this.organizationChart_serial = organizationChart_serial;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getUsrGrade() {
		return usrGrade;
	}
	public void setUsrGrade(String usrGrade) {
		this.usrGrade = usrGrade;
	}
	public String getUsrPart() {
		return usrPart;
	}
	public void setUsrPart(String usrPart) {
		this.usrPart = usrPart;
	}
}
