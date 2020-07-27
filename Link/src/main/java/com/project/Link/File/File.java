package com.project.Link.File;

import java.sql.Timestamp;

public class File {
	private String fileCode;
	private String usrId;
	private String fileType;
	private Timestamp filePostDate;
	private Timestamp fileDeleteDate;
	private int serial;
	
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Timestamp getFilePostDate() {
		return filePostDate;
	}
	public void setFilePostDate(Timestamp filePostDate) {
		this.filePostDate = filePostDate;
	}
	public Timestamp getFileDeleteDate() {
		return fileDeleteDate;
	}
	public void setFileDeleteDate(Timestamp fileDeleteDate) {
		this.fileDeleteDate = fileDeleteDate;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
}
