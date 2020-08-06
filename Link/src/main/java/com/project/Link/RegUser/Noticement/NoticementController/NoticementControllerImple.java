package com.project.Link.RegUser.Noticement.NoticementController;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementService;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementServiceImple;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.RegUser.User.UserController.UserControllerImple;
import com.project.Link.SessionControl.SessionControl;
import com.project.Link.Ufile.Service.UfileService;

//TODO NOTICEMENT POST JSP로 넘기고 연결 URL 생성

@RequestMapping(value="/notice/*")
@Controller
public class NoticementControllerImple implements NoticementController {
	private final String nFilePath = "c:\\temp\\noticement\\";
	private static final Logger logger = LoggerFactory.getLogger(NoticementControllerImple.class);
	
	@Autowired 
	@Qualifier("noticeservice")
	private NoticementService nService;
	@Autowired
	private UfileService ufService;
	
	public NoticementControllerImple() {}
	public NoticementService getnService() {return nService;}
	public void setnService(NoticementService nService) {this.nService = nService;}


	// 공지사항 목록출력 요청에 대응하는 endpoint입니다.
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@Override
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		logger.info("		Controller Level :: ListNoticements Called");
		int total = nService.totalCountNoticements();
		int targetPage = request.getParameter("page") == null ? 0 :Integer.parseInt(request.getParameter("page"))-1;
		ArrayList<Noticement> list = nService.listNoticements(targetPage);
		model.addAttribute("total", total);
		model.addAttribute("noticelist",list);
		return "/User/noticement/board";
	}
	// 특정 공지사항 조회요청에 응답하는 endpoint입니다.
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@Override
	public String GetNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		logger.info("		Controller Level :: GetNoticement Called");
		int targetSerial = Integer.valueOf(request.getParameter("n_serial"));
		Noticement noticement = nService.getNoticement(targetSerial);
		model.addAttribute("noticement", noticement);
		return "/User/noticement/read";
	}
	// 공지사항에 올라와있는 파일 다운로드 요청에 응답하는 endpoint입니다.
	@Override
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void getNoticementFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {	
		// TODO File 유효성검증 해야합니다.
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
			e.printStackTrace();
		}
	}


}
