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
	
	@RequestMapping(value={"/directlist", "/"}, method=RequestMethod.GET)
	@Override
	public String DirectListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		// 특정 유저가 작성한 게시글을 리턴해주는 페이지입니다.
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
		// directlist로 요청이 들어올때, searchTarget이 반드시 대상 User의 ID입니다.
		total = mcService.userCountCommunities(searchTarget);
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
		// 특정 댓글을 벤 합니다.
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
	@RequestMapping(value="/comment/direct", method=RequestMethod.GET)
	@Override
	//특정 유저가 작성한 댓글 리스트를 제공하는 엔드포인트입니다
	public String getUserDirectComments(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr) {
		String usrId = request.getParameter("u_id");
		int page = request.getParameter("page") != null ? Integer.valueOf((String)request.getParameter("page"))-1 : 0;
		System.out.println(usrId + " : " +page);
		ArrayList<Comment> list = mcService.getdirectUserComment(usrId, page);
		model.addAttribute("search_target", usrId);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		model.addAttribute("total", mcService.getdirectUsercommentCount(usrId));
		return "/Admin/manage/comment/board";
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
	
	@RequestMapping(value="/comment/list", method=RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		// Community 최초 로딩 외에, Paging 처리된 댓글 리스트를 제공하는 엔드포인트
		// Ajax통신을 사용하므로 ResponseBody, RequestBody를 사용함. Jackson-databind 라이브러리 사용
		int targetSerial = ajaxRequest.containsKey("c_serial") == true ? Integer.valueOf(ajaxRequest.get("c_serial")) : 0;
		if(targetSerial == 0) {
			throw new Exception();
		}
		int pageNum = ajaxRequest.containsKey("page_num") == true ? Integer.valueOf(ajaxRequest.get("page_num"))-1: 0;
		ArrayList<Comment> list = mcService.ListCommentsAjax(targetSerial, pageNum);
		HashMap<String, ArrayList<Comment>> returnInfo = new HashMap<String, ArrayList<Comment>>();
		returnInfo.put("list", list); // 결과값을 Json형태로 리턴하기위해 HashMap 사용
		return returnInfo;
	}

}
