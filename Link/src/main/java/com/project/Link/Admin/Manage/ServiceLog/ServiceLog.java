package com.project.Link.Admin.Manage.ServiceLog;

import java.sql.Timestamp;

public class ServiceLog {
	private String ipAddr;
	private Timestamp occurTime;
	private String usrId;
	private String targetService;
	private String method;
	private String resultStatus;
	private Long requiredTime;
	
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public Timestamp getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Timestamp occurTime) {
		this.occurTime = occurTime;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getTargetService() {
		return targetService;
	}
	public void setTargetService(String targetService) {
		this.targetService = targetService;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Long getRequiredTime() {
		return requiredTime;
	}
	public void setRequiredTime(Long requiredTime) {
		this.requiredTime = requiredTime;
	}
	
	
}
