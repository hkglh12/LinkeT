package com.project.Link.Admin.Manage.ServiceLog.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface ManageServiceLogController {
	public String ListServiceLog(Model model, HttpServletRequest request, HttpSession session);
	
}
