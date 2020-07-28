package com.project.Link.Noticement.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Noticement.Service.NoticementService;
import com.project.Link.Posting.Posting;
import com.project.Link.SessionControl.SessionControl;
import com.project.Link.Ufile.Service.UfileService;
import com.project.Link.UserController.UserControllerImple;

@RequestMapping(value="/notice/*")
@Controller
public class NoticementControllerImple implements NoticementController {
	private final String nFilePath = "c:\\temp\\noticement\\";
	private static final Logger logger = LoggerFactory.getLogger(NoticementControllerImple.class);
	@Autowired 
	private NoticementService nService;
	@Autowired
	private UfileService ufService;
	@Autowired 
	private SessionControl sc;
	
	public NoticementControllerImple() {}
	public NoticementService getnService() {return nService;}
	public void setnService(NoticementService nService) {this.nService = nService;}
	public SessionControl getSc() {return sc;}
	public void setSc(SessionControl sc) {this.sc = sc;	}

	//Service Level 통합 완료
	@RequestMapping(value={"/list", ""}, method = RequestMethod.GET)
	@Override
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		logger.info("Board main, linsting called");
	
		/*HashMap<String, String> sr = sc.sessionControl(request, session, redirectAttr);
		if(sr.get("usrId")==null) {
			return "/";
		}else {*/
			logger.info("elseCallsed");
			int total = nService.totalCountNoticements();
			ArrayList<Posting> list = nService.listNoticements(request);
			model.addAttribute("total", total);
			model.addAttribute("noticelist",list);
			
			logger.info("total : " +total);
			logger.info("list : " + list.toArray().toString());
			/* } */
		return "noticeboard";
	}
	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	/*
	 * public String PostNoticement(Model model, HttpServletRequest request,
	 * HttpSession session, RedirectAttributes redirectAttr) {
	 */
	//Service레벨 통합 완료
	public String PostNoticement(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception{
		logger.info(":POSTNOTICEMENT called");
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * 
		 * if(sr.get("usrId")==null || sr.get("isAdmin")=="false") { return "failed";
		 * }else {
		 */
			logger.info(":POST Upload start");
			nService.createNoticement(mpRequest,session);
			/*
			 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
			 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
			 */
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			 return "redirect:/notice/list?page=1";
		/*}*/
	}
	//ServiceLevel 통합 완료
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@Override
	public String GetNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		logger.info(":: GetNoticement called");
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null) { return "/"; }else {
		 */

			Posting noticement = nService.getNoticement(request);
			
			/*
			 * model.addAttribute("n_serial", noticement.getSerial());
			 * model.addAttribute("u_id", noticement.getUsrId());
			 * model.addAttribute("n_title", noticement.getTitle());
			 * model.addAttribute("n_contents", noticement.getContents());
			 * model.addAttribute("f_count", noticement.getFileCount());
			 * model.addAttribute("n_createdate", noticement.getCreateDate());
			 * model.addAttribute("n_count",noticement.getNoticeCount());
			 */
			model.addAttribute("posting", noticement);
			return "readnotice";
			/* } */
	}
	//ServiceLevel 통합 종료
	//UpdateForm으로 이동할때 데이터를 재요청하기위한 곳
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String getUpdateNoticement(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(":: get-UpdateNoticement called");
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null) { return "/"; }else {
		 */
			
			Posting noticement = nService.getNoticement(request);
			/*
			 * model.addAttribute("n_serial", noticement.getSerial());
			 * model.addAttribute("u_id", noticement.getUsrId());
			 * model.addAttribute("n_title", noticement.getTitle());
			 * model.addAttribute("n_contents", noticement.getContents());
			 *
			 */
			model.addAttribute("posting",noticement);
			return "updateform";
			/* } */
	}
	//ServiceLevel 통합 시작
	//통합보류 , 사유 : 파일 변경에 대한 ISSUE
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateNoticement(Model model, MultipartHttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		logger.info("::UpdateNoticement called!");
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null || sr.get("isAdmin") == "false") { return "/"; }else
		 * {
		 */

			boolean result = nService.updateNoticement(session, request);
			/*
			 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
			 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
			 */
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			return "redirect:/notice/list?page=1";
			/* } */
	}
	//ServiceLevel 통합 종료
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null || sr.get("isAdmin") == "false") { return "/"; }else
		 * {
		 */
			boolean result = nService.deleteNoticement(session, request);
			/*
			 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
			 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
			 */
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			return "redirect:/notice/list?page=1";
			/* } */
		
	}
	@Override
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void getNoticementFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {	
		try {
			File file = new File(nFilePath+request.getParameter("fileCode"));
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
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null) {
		 * 
		 * }else {
		 * 
		 * boolean result = nService.deleteNoticement(session, request); }
		 */
	
	}
	/*
	 * Exception handler
	 * https://jaehun2841.github.io/2018/08/30/2018-08-25-spring-mvc-handle-
	 * exception/#global-%EB%A0%88%EB%B2%A8%EC%97%90%EC%84%9C%EC%9D%98-%EC%B2%98%EB%
	 * A6%AC
	 */			
	
	 @ExceptionHandler(value=Exception.class)
	 public String handleDemoException(Exception e) { 
		 logger.error(e.getMessage());
		 e.printStackTrace();
		 return "/error/404"; }
	 
	

}
