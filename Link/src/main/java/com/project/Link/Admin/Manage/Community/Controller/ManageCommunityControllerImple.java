package com.project.Link.Admin.Manage.Community.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Admin.Manage.Community.Service.ManageCommunityService;
import com.project.Link.RegUser.Comment.Comment;
import com.project.Link.RegUser.Community.Community;
import com.project.Link.RegUser.Community.Controller.CommunityControllerImple;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.Ufile.Service.UfileService;

@RequestMapping(value="/admin/manage/community/*")
@Controller
public class ManageCommunityControllerImple extends CommunityControllerImple implements ManageCommunityController{
	
	private final String cFilePath = "c:\\temp\\community\\";
	private static final Logger logger = LoggerFactory.getLogger(CommunityControllerImple.class);
	
	@Autowired
	@Qualifier("AdminCommunityService")
	private ManageCommunityService mcService;
	@Autowired
	private UfileService ufService;

	@RequestMapping(value="bulkban", method=RequestMethod.POST)
	@Override
	public String BulkDeleteCommunity(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
		boolean result = false;
		if(request.getParameterValues("bulkdel") != null){
			String[] list = request.getParameterValues("bulkdel");
			result = mcService.bulkCommunityBan(list, (String)session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			if(result == true) {
				redirectAttr.addFlashAttribute("result", result);
			}
		}else {
			return "failed";
		}
		
		return "redirect:/admin/manage/community/list?page=1&subject="+request.getParameter("subject");
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("called");
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String searchCategory = request.getParameter("search_category") == null ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null ? null : request.getParameter("search_target");
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		if(searchCategory != null) {			//검색 대상이 있다면 DB 퀄럼에 맞게 변형
			if(searchCategory.equals("title")) {
				searchCategory = "c_"+searchCategory;
			}else if(searchCategory.equals("id")) {
				searchCategory = "u_"+searchCategory;
			}else {searchCategory = null;}
		}
		ArrayList<Community> list = mcService.ListCommunities(targetPage, searchCategory, searchTarget, subject);
		int total = 0;
		total = mcService.totalCountCommunities(searchCategory, searchTarget, subject);
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		if(searchCategory != null) {
			model.addAttribute("search_category", searchCategory.substring(2,searchCategory.length()));
			model.addAttribute("search_target", searchTarget);
			model.addAttribute("subject", subject);
		}
		return "/Admin/manage/community/board";
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		System.out.println("targetSerial in controller : " + targetSerial);
		Community community = mcService.getCommunity(targetSerial);
		int pageNum = 0;
		model.addAttribute("total_comment", mcService.getCommentTotalCount(targetSerial));
		model.addAttribute("community",community);
		return "/Admin/manage/community/read";
	}
	@RequestMapping(value="/ban", method=RequestMethod.POST)
	@Override
	public String BanCommunity(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		String usrId = (String)session.getAttribute("usrId");
		String subject = request.getParameter("subject");
		boolean result = mcService.banCommunity(targetSerial, usrId);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		redirectAttr.addFlashAttribute("subject", subject);
		if(result == true) {
			redirectAttr.addFlashAttribute("result", "true");
		}
		return "redirect:/admin/manage/community/list?page=1&subject="+subject;
	}
	@RequestMapping(value="/comment/ban", method=RequestMethod.POST)
	@Override
	public String BanComment(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr) {
		String usrId = (String)session.getAttribute("usrId");
		int targetSerial = request.getParameter("del_serial") != null ? Integer.valueOf((String)request.getParameter("del_serial")) : -1;
		boolean result = false;
		if(targetSerial != -1) {
			result = mcService.banComment(targetSerial, usrId);
		}else{
			return "failed";
		}
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		if(result == true) {
			redirectAttr.addFlashAttribute("result", result);
		}
		return "redirect:/admin/manage/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}
}
