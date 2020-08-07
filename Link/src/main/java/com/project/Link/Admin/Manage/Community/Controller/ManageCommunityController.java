package com.project.Link.Admin.Manage.Community.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.RegUser.Community.Controller.CommunityController;

public interface ManageCommunityController extends CommunityController{
	public String BanCommunity(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr);
	public String BulkDeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	public String BanComment(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr);
}
