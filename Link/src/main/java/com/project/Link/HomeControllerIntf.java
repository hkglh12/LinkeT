package com.project.Link;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface HomeControllerIntf {
	public String home(Locale locale, Model model, HttpSession session);
	public String signup(Locale locale, Model model);
	
}
