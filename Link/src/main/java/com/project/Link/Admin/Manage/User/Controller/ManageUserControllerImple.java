package com.project.Link.Admin.Manage.User.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Admin.Manage.Comment.Service.ManageCommentService;
import com.project.Link.Admin.Manage.Community.Service.ManageCommunityService;
import com.project.Link.Admin.Manage.User.Service.ManageUserService;
import com.project.Link.RegUser.User.User;


@RequestMapping(value="/admin/manage/user/*")
@Controller
public class ManageUserControllerImple implements ManageUserController {
	@Autowired
	@Qualifier("ManageUserService")
	private ManageUserService muService;
	@Autowired
	@Qualifier("ManageCommunityService")
	private ManageCommunityService mcService;
	@Autowired
	@Qualifier("ManageCommentService")
	private ManageCommentService mccService;
	
	public ManageUserService getMuService() {return muService;}
	public void setMuService(ManageUserService muService) {this.muService = muService;}
	public ManageCommunityService getMcService() {return mcService;}
	public void setMcService(ManageCommunityService mcService) {this.mcService = mcService;}
	
	@RequestMapping(value= {"/list","/"}, method=RequestMethod.GET)
	@Override
	public String getUserList(Model model, HttpServletRequest request, HttpSession session) {
		String mainCategory = request.getParameter("main_category") == null ? "all" : (String)request.getParameter("main_category");
		String subCategory = request.getParameter("sub_category") == null ? "" : (String)request.getParameter("sub_category");
		String searchTarget = request.getParameter("search_target") == null ? "" : request.getParameter("search_target");
		int page = request.getParameter("page") == null ? 0 : Integer.valueOf((String)request.getParameter("page"))-1;
		ArrayList<User> userList = muService.getUsers(mainCategory, page, searchTarget, subCategory);
		int totalUser = muService.getCountUser(mainCategory, subCategory, searchTarget);
		model.addAttribute("total", totalUser);
		model.addAttribute("list", userList);
		model.addAttribute("main_category", mainCategory);
		model.addAttribute("sub_category", subCategory);
		model.addAttribute("search_target", searchTarget);
		model.addAttribute("page", page+1);
		return "/Admin/manage/user/board";
	}
	@RequestMapping(value="/ban", method=RequestMethod.POST)
	@Override
	public String banUser(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		String[] targetList = request.getParameterValues("target_list");
		String category = request.getParameter("category");
		String usrId = (String)session.getAttribute("usrId");
		int page = request.getParameter("curr_page") == null ? 0 : Integer.valueOf((String)request.getParameter("curr_page"))-1;
		boolean result = muService.banUsers(targetList, usrId);
		
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		redirectAttr.addFlashAttribute("page", page+1);
		if(result == true) {
			redirectAttr.addFlashAttribute("result", "true");
		}
		return "redirect:/admin/manage/user/list?category="+category+"&page="+(page+1);
	}
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	@Override
	public String getUserDetail(Model model, HttpServletRequest request, HttpSession session) {
		String usrId = request.getParameter("user_id");
		System.out.println("in control : " + usrId);
		User result = muService.getUserDetail(usrId);
		String mainCategory = request.getParameter("main_category") == null ? "all" : (String)request.getParameter("main_category");
		String subCategory = request.getParameter("sub_category") == null ? "" : (String)request.getParameter("sub_category");
		String searchTarget = request.getParameter("search_target") == null ? "" : request.getParameter("search_target");
		System.out.println(usrId);
		System.out.println(result.getUsrId());
		System.out.println(mainCategory);
		System.out.println(subCategory);
		System.out.println(searchTarget);
		int page = request.getParameter("page") == null ? 0 : Integer.valueOf((String)request.getParameter("page"))-1;
		model.addAttribute("user", result);
		model.addAttribute("last_community", mcService.getLastUserCommunity(usrId));
		model.addAttribute("last_comment", mccService.getLastUserComment(usrId));
		model.addAttribute("main_category", mainCategory);
		model.addAttribute("sub_category", subCategory);
		model.addAttribute("search_target", searchTarget);
		model.addAttribute("page", page+1);
		return "/Admin/manage/user/profile";
	}
}
