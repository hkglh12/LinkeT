package com.project.Link.Admin.Manage.Community.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Admin.Manage.Comment.Service.ManageCommentService;
import com.project.Link.Admin.Manage.Community.Service.ManageCommunityService;
import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Community.Community;
import com.project.Link.RegUser.Community.Controller.CommunityControllerImple;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.Ufile.Service.UfileService;

@RequestMapping(value="/admin/manage/community/*")
@Controller
public class ManageCommunityControllerImple implements ManageCommunityController{
	
	private final String cFilePath = "c:\\temp\\community\\";
	
	@Autowired
	@Qualifier("ManageCommunityService")
	private ManageCommunityService mcService;
	@Autowired
	@Qualifier("ManageCommentService")
	private ManageCommentService mccService;
	@Autowired
	private UfileService ufService;

	public ManageCommunityService getMcService() {return mcService;}
	public void setMcService(ManageCommunityService mcService) {this.mcService = mcService;}
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}

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

	@RequestMapping(value={"/list", "/"}, method=RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		String searchCategory = request.getParameter("search_category") == null ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null ? null : request.getParameter("search_target");

		ArrayList<Community> list = mcService.ListCommunities(targetPage, searchCategory, searchTarget, subject);
		list = mccService.totalCountComments(list);
		int total = mcService.totalCountCommunities(searchCategory, searchTarget, subject);
		
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		
		if(searchCategory != null) {
			model.addAttribute("search_category", searchCategory);
			model.addAttribute("search_target", searchTarget);
			model.addAttribute("subject", subject);
		}
	
		return "/Admin/manage/community/board";
	}
	
	@RequestMapping(value="/directlist", method=RequestMethod.GET)
	@Override
	public String DirectListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		// 특정 유저가 작성한 게시글을 리턴해주는 페이지입니다.
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		HashMap<String, Object> params = new HashMap<String, Object>();
		String[] searchCategories = null;
		String[] searchTargets = null;
		if(subject.equals("direct")) {
			searchCategories = request.getParameterValues("search_category") == null ? null : request.getParameterValues("search_category");
			searchTargets = request.getParameterValues("search_target") == null ? null : request.getParameterValues("search_target");
			for(int i=0; i<searchCategories.length; i++) {
				System.out.println(searchCategories[i]);
				System.out.println(searchTargets[i]);
				if((searchCategories[i]!=null && !(searchCategories[i].equals("")) && (searchTargets[i]!=null && !(searchTargets[i].equals(""))))) {
					params.put(searchCategories[i], searchTargets[i]);
				}
			}
			
		}
		ArrayList<Community> list =  mcService.DirectListCommunities(targetPage, params);
		list = mccService.totalCountComments(list);
		int	total = mcService.directCountCommunities(params);
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		if(searchCategories != null) {
			model.addAttribute("search_category", searchCategories);
			model.addAttribute("search_target", searchTargets);
			for(String str : searchTargets) {
				System.out.println(str);
			}
			model.addAttribute("subject", subject);
		}
		return "/Admin/manage/community/board";
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = mcService.getCommunity(targetSerial);
		int pageNum=0;
		community.setComments(mccService.ListComments(community.getSerial(), pageNum));
		model.addAttribute("total_comment", mccService.totalCountComments(targetSerial));
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

	@RequestMapping(value="/download", method=RequestMethod.GET)
	@Override
	public void getCommunityFile(Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws Exception {
		try {
			File file = new File(cFilePath+request.getParameter("fileCode"));
			if(file.exists()) {
				String mimeType = Files.probeContentType(file.toPath());
				if(mimeType==null) {
					mimeType="application/octet-stream";
				}
				response.setContentType(mimeType);
				response.addHeader("Content-Disposition","attachment; filename=" + request.getParameter("fileCode"));
				response.setContentLength((int)file.length());
				
				OutputStream os = response.getOutputStream();
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[4096];
				int b=-1;
				while((b=fis.read(buffer)) != -1) {
					os.write(buffer, 0, b);
				}
				fis.close();
				os.close();
			}else {

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
