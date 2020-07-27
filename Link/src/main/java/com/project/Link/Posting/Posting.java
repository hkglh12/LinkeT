package com.project.Link.Posting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.project.Link.Ufile.UFile;

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
	private ArrayList<UFile> uFileList;
	
	public Posting() {
		uFileList = new ArrayList<UFile>();
	}
	
	public int getNoticeCount() {
		return noticeCount;
	}
	public ArrayList<UFile> getuFileList() {
		return uFileList;
	}

	public void setuFileList(ArrayList<UFile> uFileList) {
		this.uFileList = uFileList;
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
