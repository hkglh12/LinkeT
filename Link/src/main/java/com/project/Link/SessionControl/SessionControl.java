package com.project.Link.SessionControl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface SessionControl {
	public HashMap<String, String> sessionControl(HttpServletRequest request,HttpSession session, RedirectAttributes redirecattr);
	public HashMap<String, String> sessionControl(HttpSession session);
}
