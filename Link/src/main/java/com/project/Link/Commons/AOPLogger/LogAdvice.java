package com.project.Link.Commons.AOPLogger;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

public interface LogAdvice {
	// 로거의 DB PK로 쓰일 Timestamp를 Sessino에 삽입
	public void sessionTimeStamper(HttpSession session);
	public void EnterServiceLevel() throws Throwable;
	public void SuccessServiceLevel() throws Throwable;
	public void FailedServiceLevel() throws Throwable;
	
	//public Object log(ProceedingJoinPoint pjp) throws Throwable;
	
	
}
