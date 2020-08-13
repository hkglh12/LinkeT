package com.project.Link.Commons.AOPLogger.Dao;

public interface DaoLevelLoggerDao {
	public void loggerAfter(String ip, String sessionStamp, String usrId, String targetUri, String sqlquery);
}
