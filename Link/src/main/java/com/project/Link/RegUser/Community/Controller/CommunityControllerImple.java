package com.project.Link.RegUser.Community.Controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Comment.Comment;
import com.project.Link.Posting.Posting;
import com.project.Link.RegUser.Community.Community;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.Ufile.Service.UfileService;



@RequestMapping(value="/community/*")
@Controller
public class CommunityControllerImple implements CommunityController{
	private final String cFilePath = "c:\\temp\\community\\";
	private static final Logger logger = LoggerFactory.getLogger(CommunityControllerImple.class);

	 @Autowired
	 private CommunityService cService;
	 @Autowired
	 private UfileService ufService;
	 
	 public CommunityControllerImple() {} 
	 public CommunityService getcService() {return cService; }
	 
	public void setcService(CommunityService cService) { this.cService = cService; }
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}
	
	@RequestMapping(value= "/form", method=RequestMethod.GET)
	@Override
	public String getPostTemplate(Model model, HttpServletRequest request, HttpSession session) {
		String subject = request.getParameter("subject") == null ? "" : request.getParameter("subject");
		model.addAttribute("subect", subject);
		return "/User/community/post";		
	}
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String searchCategory = request.getParameter("search_category") == null ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null ? null : request.getParameter("search_target");
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		if(searchCategory != null) {
			if(searchCategory.equals("title")) {searchCategory = "c_"+searchCategory;
			}else if(searchCategory.equals("id")) {searchCategory = "u_"+searchCategory;
			}else {searchCategory = null;}
		}
		
		System.out.println(searchCategory);
		System.out.println(searchCategory);
		System.out.println(subject);
		ArrayList<Community> list = cService.ListCommunities(targetPage, searchCategory, searchTarget, subject);
		int total = 0;
		total = cService.totalCountCommunities(searchCategory, searchTarget, subject);
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		
		logger.info("total : " +total);
		logger.info("communitylist : " + list.toArray().toString());
			/* } */
		// 리턴 동일페이지로하고, flash attr써서 JSP에서 처리
		if(searchCategory != null) {
			//Redirect하는 개체가 아닙니다. 따라서 add attr.
		
			model.addAttribute("search_category", searchCategory.substring(2,searchCategory.length()));
			model.addAttribute("search_target", searchTarget);
		}
		return "/User/community/board";
		//}
		
	}
	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	public String PostCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		//HashMap<String, String> sr = sessionControl(session);
		//if(sr.get("usrId")=="") {
		//	return "main";
		//}else {
		/*
		 * String usrId = (String)session.getAttribute("usrId"); String cmtyTitle =
		 * request.getParameter("c_title"); String cmtyContents =
		 * request.getParameter("c_contents"); int fileCount =
		 * Integer.valueOf((String)request.getParameter("f_count"));
		 */
			logger.info(":POST Upload start");
			
			String usrId = (String)session.getAttribute("usrId");
			String title = mpRequest.getParameter("c_title");
			String contents = mpRequest.getParameter("c_contents");
			String subject = mpRequest.getParameter("c_subject");
			List<MultipartFile> uFileList = mpRequest.getFiles("u_files");
			cService.createCommunity(usrId, title, contents, uFileList, subject);
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			
			return "redirect:/community/list";
		//}
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		//HashMap<String, String> sr = sessionControl(session);
		//if(sr.get("usrId")=="") {
		//	return "main";
		//}else {
		/* int targetSerial = Integer.valueOf(request.getParameter("serial")); */
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		System.out.println("targetSerial in controller : " + targetSerial);
		Community community = cService.getCommunity(targetSerial);
			/*
			 * model.addAttribute("n_serial", community.getSerial());
			 * model.addAttribute("u_id", community.getUsrId());
			 * model.addAttribute("n_title", community.getTitle());
			 * model.addAttribute("n_contents", community.getContents());
			 * model.addAttribute("f_count", community.getFileCount());
			 * model.addAttribute("n_createdate", community.getCreateDate());
			 */
			logger.info("		RequestURICHECK " + request.getRequestURI());
			model.addAttribute("total_comment", cService.getCommentTotalCount(targetSerial));
			model.addAttribute("community",community);
			return "/User/community/read";
		//}
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String getUpdateCommunity(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(":: get-UpdateNoticement called");
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null) { return "/"; }else {
		 */
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = cService.getCommunity(targetSerial);
			/*
			 * model.addAttribute("n_serial", noticement.getSerial());
			 * model.addAttribute("u_id", noticement.getUsrId());
			 * model.addAttribute("n_title", noticement.getTitle());
			 * model.addAttribute("n_contents", noticement.getContents());
			 *
			 */
		model.addAttribute("community",community);
			//
		return "communityUpdateForm";
			/* } */
	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		//HashMap<String, String> sr = sessionControl(session);
		//if(sr.get("usrId")=="" || sr.get("isAdmin") == "false") {
		//	return "main";
		//}else {
		/*
		 * int targetSerial = Integer.valueOf(request.getParameter("serial")); //TODO
		 * 변경하세여! // String usrId = sr.get("usrId"); int ntcSerial =
		 * Integer.valueOf((String)request.getParameter("c_serial")); String ntcTitle =
		 * request.getParameter("c_title"); String ntcContents =
		 * request.getParameter("c_contents"); int fileCount =
		 * Integer.valueOf((String)request.getParameter("f_count")); boolean result =
		 * cService.updateCommunity(targetSerial, ntcTitle, ntcContents, fileCount) >= 1
		 * ? true : false;
		 */
		//Serial 기준으로 동작하므로, subject 불필요
		String usrId = (String)session.getAttribute("usrId");
		int serial = Integer.valueOf((String)mpRequest.getParameter("c_serial"));
		String title = mpRequest.getParameter("c_title");
		String contents = mpRequest.getParameter("c_contents");
		List<String> previousFileCodes = null;
		if(mpRequest.getParameterValues("previous") != null) {
			previousFileCodes = Arrays.asList(mpRequest.getParameterValues("previous"));	
		}
		List<String> deleteFileCodes = null;
		if(mpRequest.getParameterValues("del_target")!=null) {
			deleteFileCodes = Arrays.asList(mpRequest.getParameterValues("del_target"));
		}
		List<MultipartFile> uFileList = null;
		if(mpRequest.getFiles("u_files")!=null) {
			uFileList = mpRequest.getFiles("u_files");
		}
		boolean result = cService.updateCommunity(usrId, serial, title, contents, previousFileCodes, deleteFileCodes, uFileList);
		/*
		 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
		 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
		 */
		
		
		
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		return "redirect:/community/list";
		//	return "communityBoard";
		//}
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		//HashMap<String, String> sr = sessionControl(session);
		//if(sr.get("usrId")=="" || sr.get("isAdmin") == "false") {
		//	return "main";
		//}else {
		/*
		 * int targetSerial = Integer.valueOf(request.getParameter("serial")); int
		 * ntcSerial = Integer.valueOf((String)request.getParameter("n_serial"));
		 * boolean result = cService.deleteNoticement(targetSerial) >= 1 ? true : false;
		 * return "/community";
		 */int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
			boolean result = cService.deleteCommunity(targetSerial);
			/*
			 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
			 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
			 */
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			return "redirect:/community/list";
		//}
	}

	/* 개별적 댓글 List get */
	@RequestMapping(value="/comment/list", method=RequestMethod.POST)
	@Override
	@ResponseBody
										//Jackson-databind로 인해서, 같은 모양새를 가졌기 때문에 자동바인드
										// 아니면 @RequestBody로 열고 VO에 받던가 String 하나하나 이름맞춰가면서해야함
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		System.out.println(request.toString());
		//int targetSerial = request.getParameter("c_serial") != null ? Integer.valueOf((String)request.getParameter("c_serial")) : -1;
		/*int targetSerial = Integer.valueOf((String)request.getParameter("c_serial"));*/
		// AJAx통신에서 >> Json, <pom.xml dependency에 json-databind 추가했다면,> @RequestBody 위 설명 참고
		int targetSerial = ajaxRequest.containsKey("c_serial") == true ? Integer.valueOf(ajaxRequest.get("c_serial")) : 0;
		if(targetSerial == 0) {
			throw new Exception();
		}
		//int pageNum = request.getParameter("page_num") != null ? Integer.valueOf((String)request.getParameter("page_num"))-1 : 0;
		int pageNum = ajaxRequest.containsKey("page_num") == true ? Integer.valueOf(ajaxRequest.get("page_num"))-1: 0;
		System.out.println(targetSerial);
		System.out.println(pageNum);
		
		logger.info("		ListCommentAjax : " + targetSerial + " : " + pageNum);
		ArrayList<Comment> list = cService.ListCommentsAjax(targetSerial, pageNum);
		// ResponseBody 이므로 객체 자체가 전달.
		// Ajax Response의 "서버쪽 유효응답"이 XML, html, script, json, jsonp, text 이므로
		// 부득이하게 list 자체를 넘기지 못하고, JSON으로 전달
		//return list;
		HashMap<String, ArrayList<Comment>> returnInfo = new HashMap<String, ArrayList<Comment>>();
		returnInfo.put("list", list);
		return returnInfo;
		
	}
	
	@RequestMapping(value="/comment/post", method=RequestMethod.POST)
	@Override
	public String PostComments(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
			String usrId = (String)session.getAttribute("usrId");
			int targetSerial = Integer.valueOf((String)request.getParameter("c_serial"));
			String contents = request.getParameter("cc_contents");
			boolean isSecret = request.getParameter("is_secret") != null ? Boolean.valueOf(request.getParameter("issecret")) : false;
			logger.info("		RequestURICHECK " + request.getRequestURI());
			cService.createComment(usrId, targetSerial, contents, isSecret);
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			return "redirect:/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}
	@RequestMapping(value="/comment/update", method=RequestMethod.POST)
	@Override
	public String UpdateComments(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
		
		int targetSerial = Integer.valueOf((String)request.getParameter("cc_serial"));
		String contents = request.getParameter("modi_contents");
		boolean isSecret = request.getParameter("is_secret") != null ? Boolean.valueOf(request.getParameter("is_secret")) : false;
		cService.updateComment(targetSerial, contents, isSecret);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		return "redirect:/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}
	@RequestMapping(value="/comment/delete", method=RequestMethod.POST)
	@Override
	public String DeleteComments(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttr) {
		String usrId = (String)session.getAttribute("usrId");
		int targetSerial = Integer.valueOf((String)request.getParameter("del_serial"));
		cService.deleteComment(usrId, targetSerial);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		return "redirect:/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
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

	 @ExceptionHandler(value=Exception.class)
	 public String handleDemoException(Exception e) { 
		 logger.error(e.getMessage());
		 e.printStackTrace();
		 return "/error/404"; }
	


}
