package com.project.Link.Posting;

import java.sql.Timestamp;

public class Posting {
	private int serial;
	private String usrId;
	private String title;
	private String contents;
	private int fileCount;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private Timestamp deleteDate;
	private int noticeCount;
	
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int ntcSerial) {
		this.serial = ntcSerial;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String ntcTitle) {
		this.title = ntcTitle;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String ntcContents) {
		this.contents = ntcContents;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp ntcCreateDate) {
		this.createDate = ntcCreateDate;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp ntcModifyDate) {
		this.modifyDate = ntcModifyDate;
	}
	public Timestamp getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Timestamp ntcDeleteDate) {
		this.deleteDate = ntcDeleteDate;
	}
}
