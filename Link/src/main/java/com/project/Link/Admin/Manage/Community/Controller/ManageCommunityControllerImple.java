package com.project.Link.Admin.Manage.Community.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.project.Link.Commons.Community.Community;
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

	//단일 게시글 Get
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = mcService.getCommunity(targetSerial);
		// 최초 게시글 load시에는 댓글 리스트는 가장 첫 블록
		int pageNum=0;
		community.setComments(mccService.ListComments(community.getSerial(), pageNum));
		model.addAttribute("total_comment", mccService.totalCountComments(targetSerial));
		model.addAttribute("community",community);
		return "/Admin/manage/community/read";
	}
	// 게시글 리스팅
	@RequestMapping(value={"/list", "/"}, method=RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		String searchCategory = request.getParameter("search_category") == null || request.getParameter("search_category").equals("") ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null || request.getParameter("search_target").equals("") ? null : request.getParameter("search_target");

		ArrayList<Community> list = mcService.ListCommunities(targetPage, searchCategory, searchTarget, subject);
		list = mccService.totalCountComments(list);
		int total = mcService.totalCountCommunities(searchCategory, searchTarget, subject);
		
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		// Front에서 리스트 블록 접근을 위해 attr 추가
		if(searchCategory != null) {
			model.addAttribute("search_category", searchCategory);
			model.addAttribute("search_target", searchTarget);
			model.addAttribute("subject", subject);
		}
		return "/Admin/manage/community/board";
	}
	// 특정 유저의 게시글을 검색하여 리스팅, 이때 게시판 종류는 고려되지 아니함
	@RequestMapping(value="/directlist", method=RequestMethod.GET)
	@Override
	public String DirectListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		// 특정 유저가 작성한 게시글을 리턴해주는 페이지입니다.
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		HashMap<String, Object> params = new HashMap<String, Object>();
		String[] searchCategories = null;
		String[] searchTargets = null;
		searchCategories = request.getParameterValues("search_category") == null ? null : request.getParameterValues("search_category");
		searchTargets = request.getParameterValues("search_target") == null ? null : request.getParameterValues("search_target");
		for(int i=0; i<searchCategories.length; i++) {
			if((searchCategories[i]!=null && !(searchCategories[i].equals("")) && (searchTargets[i]!=null && !(searchTargets[i].equals(""))))) {
				params.put(searchCategories[i], searchTargets[i]);
			}
		}
		// 시도한거 >> controller에서 service에 매핑해준다 가 표준이라는걸 읽었었을때 변경한것. 반대로 순환호출만 안하면 문제없다고 했던것도 있었다.
		ArrayList<Community> list =  mcService.DirectListCommunities(targetPage, params);
		list = mccService.totalCountComments(list);
		int	total = mcService.directCountCommunities(params);
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		if(searchCategories != null) {
			model.addAttribute("search_category", searchCategories);
			model.addAttribute("search_target", searchTargets);
			model.addAttribute("subject", subject);
		}
		return "/Admin/manage/community/board";
	}
	//특정 게시물 삭제, 글 자세히 읽기 페이지에서 동작
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
	// 여러개 bulk delete. 보드 리스트에서 동작하는 삭제부분
	@RequestMapping(value="bulkban", method=RequestMethod.POST)
	@Override
	public String BulkBanCommunity(Model model, HttpServletRequest request, HttpSession session,
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

	@RequestMapping(value="/download", method=RequestMethod.GET)
	@Override
	public void getCommunityFile(Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws Exception {
		try {
			String fileCode = request.getParameter("fileCode");
			if(mcService.validateCommunityFile(fileCode)) {
			File file = new File(cFilePath+fileCode);
			if(file.exists()) {
				mcService.fileAccessLog();
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
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
