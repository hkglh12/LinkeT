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
import com.project.Link.RegUser.User.User;
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
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImple.class);
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@Override
	public String LoginAdmin(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {
		logger.info("/Admin/login called");
		String usrId = request.getParameter("u_id");
		String usrPw = request.getParameter("u_pw");
		User admin = aService.adminGet(usrId, usrPw);
		if(admin==null) {
			System.out.println("왜 널이야");
			model.addAttribute("result", "failed");
			return "/Admin/admin/login";
		}else {
			boolean isAdmin = admin.getUsrLevel() == 999 ? true : false;
			session.setAttribute("adminId", admin.getUsrId());
			session.setAttribute("isAdmin", isAdmin);
		}
		return "/Admin/main";
	}

}
