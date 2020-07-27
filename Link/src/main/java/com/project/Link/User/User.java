package com.project.Link.User;

import java.sql.Timestamp;

public class User {
	private String usrId;
	private String usrPw;
	private String usrName;
	private String usrPhone;
	private String usrEmail;
	private int usrLevel;
	private Timestamp usrIndate;
	private Timestamp usrOutdate;
	private Timestamp usrKickedDate;
	
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
	public int getUsrLevel() {
		return usrLevel;
	}
	public void setUsrLevel(int i) {
		this.usrLevel = i;
	}
	public Timestamp getUsrIndate() {
		return usrIndate;
	}
	public void setUsrIndate(Timestamp usrIndate) {
		this.usrIndate = usrIndate;
	}
	public Timestamp getUsrOutdate() {
		return usrOutdate;
	}
	public void setUsrOutdate(Timestamp usrOutdate) {
		this.usrOutdate = usrOutdate;
	}
	public Timestamp getUsrKickedDate() {
		return usrKickedDate;
	}
	public void setUsrKickedDate(Timestamp usrKickedDate) {
		this.usrKickedDate = usrKickedDate;
	}
}
