package com.LinkeT.LinkeT.User;

import com.LinkeT.LinkeT.Team.Team;
import java.sql.Timestamp;

public class User {
	private String usrId;
	private String usrPw;
	private String usrName;
	private String usrPhone;
	private String usrEmail;
	private int usrTeamcount;
	private Timestamp signindate;
	
	public Timestamp getSignindate() {
		return signindate;
	}
	public void setSignindate(Timestamp signindate) {
		this.signindate = signindate;
	}
	public int getUsrTeamcount() {
		return usrTeamcount;
	}
	public void setUsrTeamcount(int usrTeamcount) {
		this.usrTeamcount = usrTeamcount;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getUsrPw() {
		return usrPw;
	}
	public void setUsrPw(String usrPw) {
		this.usrPw = usrPw;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getUsrPhone() {
		return usrPhone;
	}
	public void setUsrPhone(String usrPhone) {
		this.usrPhone = usrPhone;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	
}
