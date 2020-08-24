package com.project.Link;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class AdminHomeController {

	
	@RequestMapping(value = {"/admin", "/Admin"}, method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model, HttpSession session) {
		
		if(session.getAttribute("usrId")==null) {
			return "/Admin/admin/login";
		}else {
			if(session.getAttribute("isAdmin").toString() == "false") {
				session.invalidate();
				model.addAttribute("result", "denied");
				return "/Admin/admin/login";
			}else {
				System.out.println(session.getAttribute("isAdmin"));
				return "/Admin/main";
			}
		}
		/* return main; */
		
	}
}
