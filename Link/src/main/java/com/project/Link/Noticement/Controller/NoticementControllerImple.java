package com.project.Link.Noticement.Controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Noticement.Service.NoticementService;
import com.project.Link.Posting.Posting;
import com.project.Link.SessionControl.SessionControl;
import com.project.Link.UserController.UserControllerImple;

@RequestMapping(value="/notice/*")
@Controller
public class NoticementControllerImple implements NoticementController {

	private static final Logger logger = LoggerFactory.getLogger(NoticementControllerImple.class);

	@Autowired 
	private NoticementService nService;
	@Autowired 
	private SessionControl sc;
	 

	public NoticementControllerImple() {}
	public NoticementService getnService() {
		return nService;
	}

	public void setnService(NoticementService nService) {
		this.nService = nService;
	}
	 public SessionControl getSc() {
		return sc;
	}
	public void setSc(SessionControl sc) {
		this.sc = sc;
	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	@Override
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		logger.info("Board main, linsting called");
		HashMap<String, String> sr = sc.sessionControl(request, session, redirectAttr);
		if(sr.get("usrId")==null) {
			return "/";
		}else {
			logger.info("elseCallsed");
			// 프론트엔 1부터로 출력되지만 DB Limit 구문이 시작점을 인식하므로, 0으로 변환
			int page = Integer.valueOf(request.getParameter("page"))-1;
			int total = nService.totalCountNoticements();
			ArrayList<Posting> list = nService.listNoticements(page);
			
			
			System.out.println(list.toString());
			model.addAttribute("total", total);
			model.addAttribute("noticelist",list);
			logger.info("Requested : " + page);
			logger.info("total : " +total);
		}
		return "noticeboard";
	}
	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	public String PostNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		logger.info("/post called");
		System.out.println(session.getAttribute("usrId"));
		HashMap<String, String> sr = sc.sessionControl(session);
		System.out.println(sr.get("usrId"));
		System.out.println(sr.get("isAdmin"));
		System.out.println(request.getParameter("n_title"));
		System.out.println(request.getParameter("n_contents"));
		if(sr.get("usrId")==null || sr.get("isAdmin")=="false") {
			
			return "failed";
		}else {
			logger.info("elseCallsed");
			String usrId = (String)session.getAttribute("usrId");
			String ntcTitle = request.getParameter("n_title");
			String ntcContents = request.getParameter("n_contents");
			//TODO 파일 업데이트 연결하고나면 이부분 바꿔야함
			int fileCount = 0;
			boolean result = nService.createNoticement(usrId, ntcTitle, ntcContents,fileCount);
			System.out.println(result);
			redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
			return "redirect:/notice/list?page=1";
		}
	}
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@Override
	public String GetNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		HashMap<String, String> sr = sc.sessionControl(session);
		if(sr.get("usrId")==null) {
			return "/";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			Posting noticement = nService.getNoticement(targetSerial);
			model.addAttribute("n_serial", noticement.getSerial());
			model.addAttribute("u_id", noticement.getUsrId());
			model.addAttribute("n_title", noticement.getTitle());
			model.addAttribute("n_contents", noticement.getContents());
			model.addAttribute("f_count", noticement.getFileCount());
			model.addAttribute("n_createdate", noticement.getCreateDate());
			model.addAttribute("n_count",noticement.getNoticeCount());
			return "readnotice";
		}
	}
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String getUpdateNoticement(Model model, HttpServletRequest request, HttpSession session) {
		HashMap<String, String> sr = sc.sessionControl(session);
		if(sr.get("usrId")==null) {
			return "/";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			Posting noticement = nService.getNoticement(targetSerial);
			model.addAttribute("n_serial", noticement.getSerial());
			model.addAttribute("u_id", noticement.getUsrId());
			model.addAttribute("n_title", noticement.getTitle());
			model.addAttribute("n_contents", noticement.getContents());
			return "updateform";
		}
	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		HashMap<String, String> sr = sc.sessionControl(session);
		if(sr.get("usrId")==null || sr.get("isAdmin") == "false") {
			return "/";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			
			String usrId = sr.get("usrId");
			int ntcSerial = Integer.valueOf((String)request.getParameter("n_serial"));
			String ntcTitle = request.getParameter("n_title");
			String ntcContents = request.getParameter("n_contents");
			int fileCount = Integer.valueOf((String)request.getParameter("f_count"));
			boolean result = nService.updateNoticement(targetSerial, ntcTitle, ntcContents, fileCount);
			return "noticeread";
		}
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		HashMap<String, String> sr = sc.sessionControl(session);
		if(sr.get("usrId")==null || sr.get("isAdmin") == "false") {
			return "/";
		}else {
			int targetSerial = Integer.valueOf(request.getParameter("serial"));
			boolean result = nService.deleteNoticement(targetSerial);
			return "/notice";
		}
		
	}

}
