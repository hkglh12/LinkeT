package com.project.Link.Commons.User;

import java.sql.Timestamp;

public class User {
	private String usrId;
	private String usrPw;
	private String usrName;
	private String usrPhone;
	private String usrEmail;
	private int usrLevel;
	private Timestamp usrInDate;
	private Timestamp usrOutDate;
	private Timestamp usrKickedDate;
	private String usrBannedId;
	private int communityCount;
	private int commentCount;
	private int fileCount;
	
	public String getUsrBannedUsrId() {
		return usrBannedId;
	}
	public void setUsrBannedUsrId(String usrBannedUsrId) {
		this.usrBannedId = usrBannedUsrId;
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
	public int getUsrLevel() {
		return usrLevel;
	}
	public void setUsrLevel(int i) {
		this.usrLevel = i;
	}
	public Timestamp getUsrInDate() {
		return usrInDate;
	}
	public void setUsrInDate(Timestamp usrInDate) {
		this.usrInDate = usrInDate;
	}
	public Timestamp getUsrOutDate() {
		return usrOutDate;
	}
	public void setUsrOutDate(Timestamp usrOutDate) {
		this.usrOutDate = usrOutDate;
	}
	public Timestamp getUsrKickedDate() {
		return usrKickedDate;
	}
	public void setUsrKickedDate(Timestamp usrKickedDate) {
		this.usrKickedDate = usrKickedDate;
	}
	public String getUsrBannedId() {
		return usrBannedId;
	}
	public void setUsrBannedId(String usrBannedId) {
		this.usrBannedId = usrBannedId;
	}
	public int getCommunityCount() {
		return communityCount;
	}
	public void setCommunityCount(int communityCount) {
		this.communityCount = communityCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
}
