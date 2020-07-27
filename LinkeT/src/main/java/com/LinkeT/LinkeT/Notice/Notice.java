package com.LinkeT.LinkeT.Notice;

import java.sql.Timestamp;

public class Notice {
	private int noticeSerial;
	private int teamNoticeSerial;
	private String teamCode;
	private String usrId;
	private String noticeTitle;
	private String noticeContent;
	private Timestamp noticeCreatedate;
	private String noticeFileCode1;
	private String noticeFileCode2;
	
	public int getNoticeSerial() {
		return noticeSerial;
	}
	public void setNoticeSerial(int noticeSerial) {
		this.noticeSerial = noticeSerial;
	}
	public int getTeamNoticeSerial() {
		return teamNoticeSerial;
	}
	public void setTeamNoticeSerial(int teamNoticeSerial) {
		this.teamNoticeSerial = teamNoticeSerial;
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
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Timestamp getNoticeCreatedate() {
		return noticeCreatedate;
	}
	public void setNoticeCreatedate(Timestamp noticeCreatedate) {
		this.noticeCreatedate = noticeCreatedate;
	}
	public String getNoticeFileCode1() {
		return noticeFileCode1;
	}
	public void setNoticeFileCode1(String noticeFileCode1) {
		this.noticeFileCode1 = noticeFileCode1;
	}
	public String getNoticeFileCode2() {
		return noticeFileCode2;
	}
	public void setNoticeFileCode2(String noticeFileCode2) {
		this.noticeFileCode2 = noticeFileCode2;
	}


}
