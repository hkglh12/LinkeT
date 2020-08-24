package com.project.Link.RegUser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/* Intercepter:
 * 출처: https://rongscodinghistory.tistory.com/2 [악덕고용주의 개발 일기]
*/
@Component
public class SessionControlInterceptor extends HandlerInterceptorAdapter{
	
	public SessionControlInterceptor(){};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		HttpSession session = request.getSession();
		Map<String, ?> redirectAttrs = RequestContextUtils.getInputFlashMap(request);
		// 다른 POST, DELETE, 작업을 마치고 redirected 된 세션이라면
		if(redirectAttrs!=null) {
			// 정규 session 정보에 다시 재편입
			session.setAttribute("usrId", (String)redirectAttrs.get("usrId"));
			session.setAttribute("isAdmin",String.valueOf(redirectAttrs.get("isAdmin")));
			return true;
		}else if(session.getAttribute("usrId") != null) {
			// 리다이렉트중이진 않았지만, 정규 세션 정보가 있다면
			return true;
		}else {
			// 어떠한 정보도 없다면, redirect를 시행, 이때 HomeController로 리턴되게된다.
			response.sendRedirect("/Link?result=denied");
			return false;
		}
	}
}
