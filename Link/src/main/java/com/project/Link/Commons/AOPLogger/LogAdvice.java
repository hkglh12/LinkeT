package com.project.Link.Commons.AOPLogger;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

public interface LogAdvice {
	public Object log(ProceedingJoinPoint pjp) throws Throwable;
	//AOP 로거 DB PK용 세션에 삽입하여 AOP 전역에서 사용하도록 조정
	public void sessionTimeStamper(HttpSession session);
}
