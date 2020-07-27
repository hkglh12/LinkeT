package com.project.Link.SessionControl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Component
public class SessionControlImple implements SessionControl{
	public HashMap<String, String> sessionControl(HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		// sr == sessionResults
		
		HashMap<String, String> sr = new HashMap<String, String>();
		Map<String, ?> redirectAttrs = RequestContextUtils.getInputFlashMap(request);
		if(redirectAttrs != null) {
			sr.put("usrId", (String)redirectAttrs.get("usrId"));
			sr.put("isAdmin", (String)redirectAttrs.get("isAdmin"));
		}else {
			sr.put("usrId", (String) session.getAttribute("usrId"));
			sr.put("isAdmin", String.valueOf(session.getAttribute("isAdmin")));
		}
		return sr;
	}
	
	public HashMap<String, String> sessionControl(HttpSession session) {
		// sr == sessionResults
		
		HashMap<String, String> sr = new HashMap<String, String>();
			sr.put("usrId", (String) session.getAttribute("usrId"));
			sr.put("isAdmin", String.valueOf(session.getAttribute("isAdmin")));
		return sr;
	}
}
