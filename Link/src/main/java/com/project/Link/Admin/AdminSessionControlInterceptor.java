package com.project.Link.Admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/*import com.project.Link.RegUser.Noticement.NoticementController.NoticementControllerImple;*/

public class AdminSessionControlInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AdminSessionControlInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("::::ADMINPREHANDLERCALLED*************************************");
		HttpSession session = request.getSession();
		/* HashMap<String, String> adminSr = new HashMap<String, String>(); */
		Map<String, ?> redirectAttrs = RequestContextUtils.getInputFlashMap(request);
		
		if(redirectAttrs!=null) {
			session.setAttribute("usrId", (String)redirectAttrs.get("usrId"));
			session.setAttribute("isAdmin",String.valueOf(redirectAttrs.get("isAdmin")));
			return true;
		}else if(session.getAttribute("usrId") != null && session.getAttribute("isAdmin") != "false") {
			logger.info("///////////////////////sessioninfo Confirmed////////////");
			logger.info("//At regular Access///SessionControlIntercepter got this :::: // ID : " + session.getAttribute("usrId") + "isAdmin : " +session.getAttribute("isAdmin"));
			return true;
		}else {
			response.sendRedirect("/Link/admin");
			return true;
		}
	}
	
}
