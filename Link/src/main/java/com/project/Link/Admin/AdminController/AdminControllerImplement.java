package com.project.Link.Admin.AdminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Admin.AdminService.AdminService;
import com.project.Link.Commons.User.User;


@Controller
@RequestMapping(value="/admin/*", method = RequestMethod.GET)
public class AdminControllerImplement implements AdminController{
	// Admin 객체에 대한 직접 작업 == 로그인. 그 외에는 "관리" 하
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
		if(user==null) { // 검색결과가 없다면 제자리걸음
			model.addAttribute("result", "failed");
			return "/Admin/admin/login";
		}else {
			// 세션정보 설정
			// 유저 정보는 있음, 레벨 (관리자/사용자) 검사
			boolean isAdmin = user.getUsrLevel() == 99 ? true : false; 
			if(isAdmin == true) {
				session.setAttribute("usrId", user.getUsrId());
				session.setAttribute("isAdmin", isAdmin);
				return "/Admin/main";
			}else {
				// 레벨 부족시 거절
				model.addAttribute("result", "denied");
				return "/Admin/admin/login";
			}
		}
		
	}

}
