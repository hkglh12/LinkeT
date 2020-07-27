package com.project.Link.Ufile;

import java.sql.Timestamp;

public class UFile {
	private String uFileCode;
	private String usrId;
	private Long fileSize;
	private Timestamp uFilePostDate;
	private String uFileOriginName;
	private int serial;
	
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
		return uFileOriginName;
	}
	public void setuFileOriginName(String uFileOriginName) {
		this.uFileOriginName = uFileOriginName;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	
}
