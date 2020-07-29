package com.project.Link.Ufile;

import java.sql.Timestamp;

public class UFile {
	private String uFileCode;
	private String usrId;
	private Long fileSize;
	private Timestamp uFilePostDate;
	private String uFileOriginalName;
	private int serial;
	private boolean isDisConn;
	private Timestamp uFileDisConnDate;
	private String uFileDisConnId;
	
	public String getuFileOriginalName() {
		return uFileOriginalName;
	}
	public void setuFileOriginalName(String uFileOriginalName) {
		this.uFileOriginalName = uFileOriginalName;
	}
	public boolean isDisConn() {
		return isDisConn;
	}
	public void setDisConn(boolean isDisConn) {
		this.isDisConn = isDisConn;
	}
	public Timestamp getuFileDisConnDate() {
		return uFileDisConnDate;
	}
	public void setuFileDisConnDate(Timestamp uFileDisConnDate) {
		this.uFileDisConnDate = uFileDisConnDate;
	}
	public String getuFileDisConnUsrId() {
		return uFileDisConnId;
	}
	public void setuFileDisConnUsrId(String uFileDisConnUsrId) {
		this.uFileDisConnId = uFileDisConnUsrId;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getuFileCode() {
		return uFileCode;
	}
	public void setuFileCode(String uFileCode) {
		this.uFileCode = uFileCode;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public Timestamp getuFilePostDate() {
		return uFilePostDate;
	}
	public void setuFilePostDate(Timestamp uFilePostDate) {
		this.uFilePostDate = uFilePostDate;
	}
	public String getuFileOriginName() {
		return uFileOriginalName;
	}
	public void setuFileOriginName(String uFileOriginName) {
		this.uFileOriginalName = uFileOriginName;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	
}
