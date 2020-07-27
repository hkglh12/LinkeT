package com.project.Link.Community.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.Link.Community.Service.CommunityService;
import com.project.Link.Posting.Posting;



@RequestMapping(value="/community/*")
@Controller
public class CommunityControllerImple implements CommunityController{
	private static final Logger logger = LoggerFactory.getLogger(CommunityControllerImple.class);

	 @Autowired
	 private CommunityService cService;
	 
	 
	 public CommunityControllerImple() {} 
	 public CommunityService getcService() {return cService; }
	 public void setcService(CommunityService cService) { this.cService = cService; }

	
	@Override
	public HashMap<String, String> sessionControl(HttpSession session) {
		logger.info("SessionContoll called");
		// sr == sessionResults
		HashMap<String, String> sr = new HashMap<String, String>();
		sr.put("usrId", (String) session.getAttribute("usrId"));
		sr.put("isAdmin", (String) session.getAttribute("isAdmin"));
		return sr;
	}
	
	@RequestMapping(value="/")
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> sr = sessionControl(session);
		if(sr.get("usrId")==""){
			return "main";
		}else {
			int page = Integer.valueOf(request.getParameter("page"));
			return "communityboard";
		}
		
	}
	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	public String PostCommunity(Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> sr = sessionControl(session);
		if(sr.get("usrId")=="") {
			return "main";
		}else {
			String usrId = (String)session.getAttribute("usrId");
			String cmtyTitle = request.getParameter("c_title");
			String cmtyContents = request.getParameter("c_contents");
			int fileCount = Integer.valueOf((String)request.getParameter("f_count"));
			
			boolean result = cService.createCommunity(usrId, cmtyTitle, cmtyContents, fileCount) >= 1 ? true : false;
			return "/community";
		}
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> sr = sessionControl(session);
		if(sr.get("usrId")=="") {
			return "main";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			Posting community = cService.getCommunity(targetSerial);
			model.addAttribute("n_serial", community.getSerial());
			model.addAttribute("u_id", community.getUsrId());
			model.addAttribute("n_title", community.getTitle());
			model.addAttribute("n_contents", community.getContents());
			model.addAttribute("f_count", community.getFileCount());
			model.addAttribute("n_createdate", community.getCreateDate());
			return "communityread";
		}
	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateCommunity(Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> sr = sessionControl(session);
		if(sr.get("usrId")=="" || sr.get("isAdmin") == "false") {
			return "main";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			
			String usrId = sr.get("usrId");
			int ntcSerial = Integer.valueOf((String)request.getParameter("c_serial"));
			String ntcTitle = request.getParameter("c_title");
			String ntcContents = request.getParameter("c_contents");
			int fileCount = Integer.valueOf((String)request.getParameter("f_count"));
			boolean result = cService.updateCommunity(targetSerial, ntcTitle, ntcContents, fileCount) >= 1 ? true : false;
			return "communityboard";
		}
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteCommunity(Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> sr = sessionControl(session);
		if(sr.get("usrId")=="" || sr.get("isAdmin") == "false") {
			return "main";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			int ntcSerial = Integer.valueOf((String)request.getParameter("n_serial"));
			boolean result = cService.deleteNoticement(targetSerial) >= 1 ? true : false;
			return "/cpmmunity";
		}
	}

}
