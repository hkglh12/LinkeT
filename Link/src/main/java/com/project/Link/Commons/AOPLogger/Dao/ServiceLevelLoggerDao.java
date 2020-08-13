package com.project.Link.Commons.AOPLogger.Dao;

public interface ServiceLevelLoggerDao {
	public void loggerBefore(String ip, String sessionStamp, String usrId, String targetUri);
}
