package com.project.Link.Admin.Manage.User.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.User.User;

public interface ManageUserController {
	public String getUserList(Model model, HttpServletRequest request, HttpSession session);
	public String banUser(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	public String getUserDetail(Model model, HttpServletRequest request, HttpSession session);
}
