package com.project.Link.RegUser.Noticement.NoticementController;

import java.io.File;
import java.io.FileInputStream;

import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;
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

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementService;
import com.project.Link.Ufile.Service.UfileService;


@RequestMapping(value="/notice/*")
@Controller
public class NoticementControllerImple implements NoticementController {
	private final String nFilePath = "c:\\temp\\noticement\\";
	
	@Autowired 
	@Qualifier("noticeservice")
	private NoticementService nService;
	@Autowired
	private UfileService ufService;
	
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}
	public NoticementControllerImple() {}
	public NoticementService getnService() {return nService;}
	public void setnService(NoticementService nService) {this.nService = nService;}


	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@Override
	// 공지사항 리스팅에 응답하는 endpoint
	public String ListNoticements(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		int total = nService.totalCountNoticements();	// 삭제처리 되지 않은 공지사항 개수를 출력 
		int targetPage = request.getParameter("page") == null ? 0 :Integer.parseInt(request.getParameter("page"))-1; // 페이징처리
		ArrayList<Noticement> list = nService.listNoticements(targetPage);
		model.addAttribute("total", total);
		model.addAttribute("noticelist",list);
		return "/User/noticement/board";
	}

	@RequestMapping(value="/get", method = RequestMethod.GET)
	@Override
	// 특정 공지사항 조회요청에 응답하는 endpoint
	public String GetNoticement(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("n_serial"));	// serial로 동작
		Noticement noticement = nService.getNoticement(targetSerial);
		model.addAttribute("noticement", noticement);
		return "/User/noticement/read";
	}
	@Override
	@RequestMapping(value="/download", method=RequestMethod.GET)
	// 공지사항에 올라와있는 파일 다운로드 요청에 응답하는 endpoint입니다.
	public void getNoticementFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {	
		try {
			String fileCode = request.getParameter("fileCode");
			if(nService.validateNoticementFile(fileCode)) { // 파일코드 유효성 검사
				File file = new File(nFilePath+fileCode);
				if(file.exists()) {
					nService.fileAccessLog();
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
