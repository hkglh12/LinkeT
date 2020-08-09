package com.project.Link;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface AdminHomeContollerIntf {
	public String adminHome(Locale locale, Model model, HttpSession session);
}
