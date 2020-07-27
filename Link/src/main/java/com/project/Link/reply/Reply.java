package com.project.Link.reply;

import java.sql.Timestamp;

public class Reply {
	private int replySerial;
	private String usrId;
	private int serial;
	private String replyContents;
	private Timestamp replyCreateDate;
	private Timestamp replayModifyDate;
	
	public int getReplySerial() {
		return replySerial;
	}
	public void setReplySerial(int replySerial) {
		this.replySerial = replySerial;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getReplyContents() {
		return replyContents;
	}
	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}
	public Timestamp getReplyCreateDate() {
		return replyCreateDate;
	}
	public void setReplyCreateDate(Timestamp replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}
	public Timestamp getReplayModifyDate() {
		return replayModifyDate;
	}
	public void setReplayModifyDate(Timestamp replayModifyDate) {
		this.replayModifyDate = replayModifyDate;
	}
	public Timestamp getReplyDeleteDate() {
		return replyDeleteDate;
	}
	public void setReplyDeleteDate(Timestamp replyDeleteDate) {
		this.replyDeleteDate = replyDeleteDate;
	}
	private Timestamp replyDeleteDate;
}
