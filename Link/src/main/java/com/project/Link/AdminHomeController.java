package com.project.Link;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class AdminHomeController {
	public static final Logger logger = LoggerFactory.getLogger(AdminHomeController.class);
	
	@RequestMapping(value = {"/admin", "/Admin"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("///////////////////////////////////////ADMIN ACCESS TRIED");
		if(session.getAttribute("usrId")==null) {
			return "/Admin/admin/login";
		}else {
			if(session.getAttribute("isAdmin") == "false") {
				session.invalidate();
				model.addAttribute("result", "notvalid");
				return "/Admin/admin/login";
			}
			return "/Admin/main";
		}
		/* return main; */
		
	}
}
