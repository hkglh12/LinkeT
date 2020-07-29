package com.project.Link.Community.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Community.Community;
import com.project.Link.Community.Service.CommunityService;
import com.project.Link.Posting.Posting;
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
	
	 //Session Control 이제 Interceptor가 합니당
	//@Override
	//public HashMap<String, String> sessionControl(HttpSession session) {
	//	logger.info("SessionContoll called");
		// sr == sessionResults
	//	HashMap<String, String> sr = new HashMap<String, String>();
	//	sr.put("usrId", (String) session.getAttribute("usrId"));
	//	sr.put("isAdmin", (String) session.getAttribute("isAdmin"));
	//	return sr;
	//}
	@RequestMapping(value= "/form", method=RequestMethod.GET)
	@Override
	public String getPostTemplate(Model model, HttpServletRequest reqeust, HttpSession session) {
		return "communityPost";		
	}
	@RequestMapping(value={"/list", ""}, method = RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		//HashMap<String, String> sr = sessionControl(session);
		//if(sr.get("usrId")==""){
		//	return "main";
		//}else {
			//int page = Integer.valueOf(request.getParameter("page"));
		// 요청이 들어오면 총 게시판 개수를 제공
		//ArrayList<Community> list = cService.ListCommunities(request);
		logger.info("Board main, linsting called");
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		/*HashMap<String, String> sr = sc.sessionControl(request, session, redirectAttr);
		if(sr.get("usrId")==null) {
			return "/";
		}else {*/
			logger.info("elseCallsed");
			int total = cService.totalCountCommunities();
			ArrayList<?> list = cService.ListCommunities(targetPage);
			model.addAttribute("total", total);
			model.addAttribute("communitylist",list);
			
			logger.info("total : " +total);
			logger.info("communitylist : " + list.toArray().toString());
			/* } */
			System.out.println("왜또안되냐뭐가문제냐");
			return "communityBoard";
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
			List<MultipartFile> uFileList = mpRequest.getFiles("u_files");
			cService.createCommunity(usrId, title, contents, uFileList);
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
		Community community = cService.getCommunity(targetSerial);
			/*
			 * model.addAttribute("n_serial", community.getSerial());
			 * model.addAttribute("u_id", community.getUsrId());
			 * model.addAttribute("n_title", community.getTitle());
			 * model.addAttribute("n_contents", community.getContents());
			 * model.addAttribute("f_count", community.getFileCount());
			 * model.addAttribute("n_createdate", community.getCreateDate());
			 */
			model.addAttribute("community",community);
			return "communityRead";
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

		}
		
	}
	 @ExceptionHandler(value=Exception.class)
	 public String handleDemoException(Exception e) { 
		 logger.error(e.getMessage());
		 e.printStackTrace();
		 return "/error/404"; }
	
	 
	


}
