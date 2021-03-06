package com.project.Link.Admin.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.AdminHomeController;
import com.project.Link.Admin.AdminService.AdminService;
import com.project.Link.Commons.User.User;
import com.project.Link.RegUser.User.UserController.UserControllerImple;

@Controller
@RequestMapping(value="/admin/*", method = RequestMethod.GET)
public class AdminControllerImplement implements AdminController{
	
	@Autowired
	private AdminService aService;
	
	public AdminService getaService() {
		return aService;
	}
	public void setaService(AdminService aService) {
		this.aService = aService;
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	@Override
	public String LoginAdmin(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
		
		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		User user = aService.adminGet(usrId, usrPw);
		if(user==null) {
			model.addAttribute("result", "failed");
			return "/Admin/admin/login";
		}else {
			boolean isAdmin = user.getUsrLevel() == 99 ? true : false;
			if(isAdmin == true) {
				session.setAttribute("usrId", user.getUsrId());
				session.setAttribute("isAdmin", isAdmin);
				return "/Admin/main";
			}else {
				model.addAttribute("result", "denied");
				return "/Admin/admin/login";
			}
		}
		
	}

}
