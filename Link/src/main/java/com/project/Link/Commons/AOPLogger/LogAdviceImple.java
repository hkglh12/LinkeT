package com.project.Link.Commons.AOPLogger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.Link.Commons.AOPLogger.Dao.DaoLevelLoggerDao;
import com.project.Link.Commons.AOPLogger.Dao.ServiceLevelLoggerDao;

@Component
@Aspect
public class LogAdviceImple implements LogAdvice{
	
	@Autowired
	private ServiceLevelLoggerDao svlLogger;
	@Autowired
	private DaoLevelLoggerDao dlLogger;
	
	public ServiceLevelLoggerDao getSvlLogger() {
		return svlLogger;
	}

	public void setSvlLogger(ServiceLevelLoggerDao svlLogger) {
		this.svlLogger = svlLogger;
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private final Logger logger = LoggerFactory.getLogger(LogAdviceImple.class);
	// Cannot proxy target class because CGLIB2 is not available. 에러 발생
	// Admin home controller, HomeController,  SessionControlInterceptor가 Interface 없이 class기반으로만 작성되어있기 때문이다.
	// 이를 해결하기위해 CGLIB 라이브러리를 추가했다
	/*
	 * @Around("execution(* com.project.Link.Admin..service..*Impl.*(..))"
	 * +" or execution(* com.project.Link.Commons..service..*Impl.*(..))"
	 * +" or execution(* com.project.Link.RegUser..service..*Impl.*(..))"
	 * +" or execution(* com.project.Link.Ufile..service..*Impl.*(..))" )
	 */
	/*
	 * @Pointcut("execution(* com.project.Link.RegUser.Community.Service.*.*(..)) "
	 * + " or execution(* com.project.Link.Commons.Community..*.*(..))") public void
	 * serviceAccess() {}
	 */
	/*
	 * @Pointcut("execution(* com.project.Link.RegUser.Notice")
	 */
	
	/*
	 https://yeti.tistory.com/126 
	  https://doublesprogramming.tistory.com/207
	  
	  
	 https://addio3305.tistory.com/86
	 * */
	// Before, after 활용 https://sup2is.tistory.com/59
	
	@Before("@within(org.springframework.stereotype.Service)")
	//public Object EnterServiceLevel(ProceedingJoinPoint pjp) throws Throwable{
	public void EnterServiceLevel(JoinPoint jp) throws Throwable{
		 HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			logger.info("Service in*********************************************************");
		 	String usrId = null;
			String sessionStamp = null;
			String targetUri = null;
			String ip = null;
			if(request != null) {
				HttpSession session = request.getSession();
				sessionTimeStamper(session);
				usrId = (String) session.getAttribute("usrId");
				sessionStamp = (String) session.getAttribute("SessionStamp");
				targetUri = request.getRequestURI();
				ip = request.getHeader("X-FORWARDED-FOR");
				if(ip == null) {
					ip = request.getRemoteAddr();
				}
			}
			svlLogger.loggerBefore(ip, sessionStamp, usrId, targetUri);
	
			// 메서드 이름 : jp.getSignature().getName()
			logger.info("Session : " + usrId + "IP : " +ip+" / Timestsamp : " + sessionStamp + "Target :"+targetUri+" ////  Arg / param : " + Arrays.deepToString(jp.getArgs()));
			System.out.println("===========================================================");
			
	}

	/*
	 * @After("@within(org.springframework.stereotype.Component) + && !execution(* com.project.Link.Commons.AOPLogger)"
	 * ) public void AfterDaoLevel(JoinPoint jp) throws Throwable{
	 * HttpServletRequest request =
	 * ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).
	 * getRequest();
	 * logger.info("DAO OUT//////////////////////////////////////////////////////");
	 * String usrId = null; String sessionStamp = null; String targetUri = null;
	 * String ip = null; SqlSessionFactory s ; String sqlquery = null;
	 * 
	 * if(request != null) { HttpSession session = request.getSession();
	 * sessionTimeStamper(session); usrId = (String) session.getAttribute("usrId");
	 * sessionStamp = (String) session.getAttribute("SessionStamp"); targetUri =
	 * request.getRequestURI(); ip = request.getHeader("X-FORWARDED-FOR"); if(ip ==
	 * null) { ip = request.getRemoteAddr(); } } Object[] methodArgs = jp.getArgs(),
	 * sqlArgs = null;
	 * 
	 * // 실행시킨 SQL 문장 String statement = methodArgs[0].toString(); // ? 가 포함된 SQL문
	 * 
	 * // find the SQL arguments (parameters) for (int i = 1, n = methodArgs.length;
	 * i < n; i++) { Object arg = methodArgs[i]; if (arg instanceof Object[]) {
	 * sqlArgs = (Object[]) arg; break; } }
	 * 
	 * // '?' 대신 파라미터로 대체 String completedStatement = (sqlArgs == null ? statement :
	 * fillParameters(statement, sqlArgs)); sqlquery = completedStatement;
	 * 
	 * logger.info("SQLQUERY :" +completedStatement);
	 * 
	 */
			//dlLogger.loggerAfter(ip, sessionStamp, usrId, targetUri, sqlquery);
	//}
//	@Around(" execution(* com.project.Link.User.UserService..*.*(..))"
//			+" or execution(* com.project.Link.RegUser.Comment.Service..*.*(..)) "
//			+" or execution(* com.project.Link.RegUser.Noticement.NoticementService..*.*(..))"
//			+" or execution(* com.project.Link.Commons.Community.Service..*.*(..))"
//			/* +" or execution(* com.project.Link.Commons.Comment.Service..*.*(..))" */
//			+" or execution(* com.project.Link.Admin.Manage.Comment.Service..*.*(..))"
//			+" or execution(* com.project.Link.Admin.Manage.Community.Service..*.*(..))"
//			+" or execution(* com.project.Link.Admin.Manage.Noticement.Service..*.*(..))"
//			+" or execution(* com.project.Link.Admin.Manage.User.Service..*.*(..))"
//			)
	public Object log(ProceedingJoinPoint pjp) throws Throwable{
	    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String usrId = null;
		String sessionStamp = null;
		if(request != null) {
			HttpSession session = request.getSession();
			sessionTimeStamper(session);
			usrId = (String) session.getAttribute("usrId");
			sessionStamp = (String) session.getAttribute("SessionStamp");
		}
		
		Object result = pjp.proceed();
		
		logger.info("Session : " + usrId + " / Timestsamp : " + sessionStamp + "Target :"+ pjp.getSignature().getName()+" ////  Arg / param : " + Arrays.deepToString(pjp.getArgs()));
		System.out.println("===========================================================");
		return result;
	}
	@Override
	public void sessionTimeStamper(HttpSession session) {
		session.setAttribute("SessionStamp", Timestamp.valueOf(LocalDateTime.now()).toString());
	}
	/*
	 * private String fillParameters(String statement, Object[] sqlArgs) { //
	 * initialize a StringBuilder with a guesstimated final length StringBuilder
	 * completedSqlBuilder = new StringBuilder(Math.round(statement.length() *
	 * 1.2f)); int index, // will hold the index of the next ? prevIndex = 0; //
	 * will hold the index of the previous ? + 1
	 * 
	 * // loop through each SQL argument for (Object arg : sqlArgs) { index =
	 * statement.indexOf("?", prevIndex); if (index == -1) break; // bail out if
	 * there's a mismatch in # of args vs. ?'s
	 * 
	 * // append the chunk of SQL coming before this ?
	 * completedSqlBuilder.append(statement.substring(prevIndex, index)); // append
	 * the replacement for the ? if (arg == null)
	 * completedSqlBuilder.append("NULL"); else
	 * completedSqlBuilder.append(":"+arg.toString());
	 * 
	 * prevIndex = index + 1; }
	 * 
	 * // add the rest of the SQL if any if (prevIndex != statement.length())
	 * completedSqlBuilder.append(statement.substring(prevIndex));
	 * 
	 * return completedSqlBuilder.toString(); }
	 */
}
