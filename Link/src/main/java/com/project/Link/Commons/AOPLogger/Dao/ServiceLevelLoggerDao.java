package com.project.Link.Commons.AOPLogger.Dao;

import java.sql.Timestamp;

public interface ServiceLevelLoggerDao {
	public void loggerBefore(String ip, Timestamp sessionStamp, String usrId, String targetUri, String method);
	public void afterSuccess(Timestamp sessionStampStr, long requiredTime);
	public void afterFailed(Timestamp sessionStampStr, long requiredTime);
}
