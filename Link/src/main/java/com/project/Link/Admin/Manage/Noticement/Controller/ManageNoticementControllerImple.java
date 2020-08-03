package com.project.Link.Admin.Manage.Noticement.Controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Admin.Manage.Noticement.Service.ManageNoticementService;
import com.project.Link.Admin.Manage.Noticement.Service.ManageNoticementServiceImple;
import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementController.NoticementControllerImple;
import com.project.Link.Ufile.Service.UfileService;

@RequestMapping(value={"/admin/manage/notice/*", "/Admin/manage/notice/*"})
@Controller
public class ManageNoticementControllerImple extends NoticementControllerImple implements ManageNoticementController {
	private final String nFilePath = "c:\\temp\\noticement\\";
	private static final Logger logger = LoggerFactory.getLogger(NoticementControllerImple.class);
	
	// 모든 N service는 a서비스로 변경되어야합니다.
	@Autowired
	@Qualifier("manageNoticeService")
	private ManageNoticementService mnService;
	
	public ManageNoticementService getMnService() {
		return mnService;
	}

	public void setMnService(ManageNoticementService mnService) {
		this.mnService = mnService;
	}

	// 관리자용 List페이지를 적용한 jsp입니다.
	// 공지사항의 목록출력 요청에 대응하는 페이지입니다. 
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@Override
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		logger.info("		Controller Level :: ListNoticements Called");
		int total = mnService.totalCountNoticements();
		int targetPage = request.getParameter("page") == null ? 0 :Integer.parseInt(request.getParameter("page"))-1;
		ArrayList<Noticement> list = mnService.listNoticements(targetPage);
		model.addAttribute("total", total);
		model.addAttribute("noticelist",list);
		/* } */
		return "/Admin/manage/noticement/board";
	}
	
	// 공지사항 작성를 위한 "작성 폼" 요청에 응답하는 Endpoint
	@RequestMapping(value="/form", method=RequestMethod.GET)
	@Override
	public String getPostTemplate(Model model, HttpServletRequest reqeust, HttpSession session) {
		logger.info("Manage : post form");
		return "/Admin/manage/noticement/post";		
	}
	// TODO 성공했는지 아닌지 출력해야합니다.
	// 신규 공지사항 입력 요청에 응답하는 endpoint입니다.
	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	public String PostNoticement(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception{
		logger.info("		Controller Level :: PostNoticement Called");
		String usrId = (String)session.getAttribute("usrId");
		String title = mpRequest.getParameter("n_title");
		String contents = mpRequest.getParameter("n_contents");
		List<MultipartFile> uFileList = mpRequest.getFiles("u_files");
		boolean result = mnService.createNoticement(usrId, title, contents,uFileList);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		redirectAttr.addFlashAttribute("result", result);
		return "redirect:/Admin/manage/notice/list?page=1";
		/*}*/
	}
	
	// 특정 공지사항 조회 요청에 응답하는 Endpoint입니다.
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@Override
	public String GetNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		logger.info("		Controller Level :: GetNoticement Called");
		int targetSerial = Integer.valueOf(request.getParameter("n_serial"));
		Noticement noticement = mnService.getNoticement(targetSerial);
		model.addAttribute("noticement", noticement);
		return "/Admin/manage/noticement/read";
	}
	
	//Update요청을 위해 해당 경로로 접근하여 업데이트폼과 그 입력내용을 전달받음
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String getUpdateNoticement(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("		Controller Level :: getUpdateNoticement Called");
		int targetSerial = Integer.valueOf(request.getParameter("n_serial"));
		Noticement noticement = mnService.getNoticement(targetSerial);
		model.addAttribute("noticement",noticement);
		return "/Admin/manage/noticement/updateForm";
	}
	
	
	// 공지사항 Update 요청에 대응하는 Endpoint
	// TODO 성공여부를 전달하여 jsp에서 출력하게해야함
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateNoticement(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		logger.info("		Controller Level :: UpdateNoticement Called");
		/*
		 * HashMap<String, String> sr = sc.sessionControl(session);
		 * if(sr.get("usrId")==null || sr.get("isAdmin") == "false") { return "/"; }else
		 * {
		 */
		String usrId = (String)session.getAttribute("usrId");
		int serial = Integer.valueOf((String)mpRequest.getParameter("n_serial"));
		String title = mpRequest.getParameter("n_title");
		String contents = mpRequest.getParameter("n_contents");
		
		List<String> previousFileCodes = null;
		if(mpRequest.getParameterValues("previous") != null) {
			previousFileCodes = Arrays.asList(mpRequest.getParameterValues("previous"));
		}
		
		List<String> deleteFileCodes = null;
		if(mpRequest.getParameterValues("del_targets")!=null) {
			deleteFileCodes = Arrays.asList(mpRequest.getParameterValues("del_targets"));
		}
		List<MultipartFile> uFileList = null;
		if(mpRequest.getFiles("u_files")!=null) {
			uFileList= mpRequest.getFiles("u_files");
		}
		
		boolean result = mnService.updateNoticement(usrId, serial, title, contents, previousFileCodes, deleteFileCodes, uFileList);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		redirectAttr.addFlashAttribute("result", result);
		return "redirect:/Admin/manage/notice/list?page=1";
			/* } */
	}
	
	// 공지사항 삭제요청에 응답하는 페이지
	// TODO 성공 여부를 전달하여 프린팅하게해야함.
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		logger.info("		Controller Level :: DeleteNoticement Called");
		int targetSerial = Integer.valueOf(request.getParameter("n_serial"));
		String usrId = (String)session.getAttribute("usrId");
		boolean result = mnService.deleteNoticement(targetSerial, usrId);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		redirectAttr.addFlashAttribute("result", result);
		return "redirect:/Admin/manage/notice/list?page=1&result="+result;
		
	}
		/*
		 * Exception handler
		 * https://jaehun2841.github.io/2018/08/30/2018-08-25-spring-mvc-handle-
		 * exception/#global-%EB%A0%88%EB%B2%A8%EC%97%90%EC%84%9C%EC%9D%98-%EC%B2%98%EB%
		 * A6%AC
		 */			
		
	 @ExceptionHandler(value=Exception.class)
	 public String RuntimeException(Exception e) { 
		 logger.error(e.getMessage());
		 e.printStackTrace();
		 return "/error/404"; 
	}
}
