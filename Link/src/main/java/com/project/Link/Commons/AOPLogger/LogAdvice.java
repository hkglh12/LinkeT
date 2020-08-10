package com.project.Link.Commons.AOPLogger;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
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
	@Around(" execution(* com.project.Link.User.UserService..*.*(..))"
			+" or execution(* com.project.Link.RegUser.Comment.Service..*.*(..)) "
			+" or execution(* com.project.Link.RegUser.Noticement.NoticementService..*.*(..))"
			+" or execution(* com.project.Link.Commons.Community.Service..*.*(..))"
			/* +" or execution(* com.project.Link.Commons.Comment.Service..*.*(..))" */
			+" or execution(* com.project.Link.Admin.Manage.Comment.Service..*.*(..))"
			+" or execution(* com.project.Link.Admin.Manage.Community.Service..*.*(..))"
			+" or execution(* com.project.Link.Admin.Manage.Noticement.Service..*.*(..))"
			+" or execution(* com.project.Link.Admin.Manage.User.Service..*.*(..))"
			)
	public Object log(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		
		Object result = pjp.proceed();
		
		long end=System.currentTimeMillis();
		
		System.out.println(pjp.getSignature().getName());
		System.out.println("Arg / param" + Arrays.deepToString(pjp.getArgs()));
		System.out.println("===========================================================");
		return result;
	}
}
