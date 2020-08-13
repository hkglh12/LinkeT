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
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Community.Community;
import com.project.Link.RegUser.Comment.Service.CommentService;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.Ufile.Service.UfileService;



@RequestMapping(value="/community/*")
@Controller
public class CommunityControllerImple implements CommunityController{
	private final String cFilePath = "c:\\temp\\community\\";
	

	@Autowired
	@Qualifier("UserCommunityService")
	private CommunityService cService;
	@Autowired
	private UfileService ufService;
	@Autowired
	@Qualifier("UserCommentService")
	private CommentService ccService;
	 
	public CommunityControllerImple() {} 
	public CommunityService getcService() {return cService; } 
	public void setcService(CommunityService cService) { this.cService = cService; }
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}
	
	@RequestMapping(value={"/list", "/directlist", "/"}, method = RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		// 자유게시판 글 목록을 read, 한개의 테이블로 java, jsp, spring 게시글을 모아두므로, subject 컬럼이 필요함.
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String searchCategory = request.getParameter("search_category") == null ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null ? null : request.getParameter("search_target");
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		if(searchCategory != null) { // 검색어와 검색타겟이 인자에 있다면, db 컬럼 이름에 맞게 변형
			if(searchCategory.equals("title")) {
				searchCategory = "c_"+searchCategory;
			}else if(searchCategory.equals("id")) {
				searchCategory = "u_"+searchCategory;
			}else {searchCategory = null;}
		}
		ArrayList<Community> list = cService.ListCommunities(targetPage, searchCategory, searchTarget, subject);
		list = ccService.totalCountComments(list);
		int total = 0;
		if(subject.contentEquals("direct")) {
			//Direct일경우 반드시 유저 아이디가 넘어옵니다.
			total = cService.userCountCommunities(searchTarget);
		}else {
			total = cService.totalCountCommunities(searchCategory, searchTarget, subject);
		}
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		model.addAttribute("page", targetPage);
		if(searchCategory != null) {
			model.addAttribute("search_category", searchCategory.substring(2,searchCategory.length()));
			model.addAttribute("search_target", searchTarget);
			model.addAttribute("subject", subject);
		}
		return "/User/community/board";
	}

	@RequestMapping(value= "/form", method=RequestMethod.GET)
	@Override
	public String getPostTemplate(Model model, HttpServletRequest request, HttpSession session) {
		// 신규 등록을 위해, 입력 폼 요청에 대한 응답
		String subject = request.getParameter("subject") == null ? "java" : request.getParameter("subject");
		model.addAttribute("subject", subject);
		return "/User/community/post";		
	}

	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	public String PostCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		// 게시글 업로드 요청에 대응하는 엔드포인트
		String usrId = (String)session.getAttribute("usrId");
		String title = mpRequest.getParameter("c_title");
		String contents = mpRequest.getParameter("c_contents");
		String subject = mpRequest.getParameter("c_subject");
		List<MultipartFile> uFileList = mpRequest.getFiles("u_files");
		boolean result = cService.createCommunity(usrId, title, contents, uFileList, subject);
		if(result == true) {
			redirectAttr.addFlashAttribute("result", "true"); // 성공했다면 결과값 JSP로 리턴
		}
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		return "redirect:/community/list?subject="+subject+"&page=1";
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = cService.getCommunity(targetSerial);
		int pageNum=0;
		community.setComments(ccService.ListComments(community.getSerial(),pageNum));
		model.addAttribute("total_comment", ccService.totalCountComments(targetSerial));
		model.addAttribute("community",community);
		return "/User/community/read";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String getUpdateCommunity(Model model, HttpServletRequest request, HttpSession session) {
		// 게시글 수정 요청을 위해, 정보와 form 요청에 응답하는 엔드포인트
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = cService.getCommunity(targetSerial);
		model.addAttribute("community",community);
		return "/User/community/updateForm";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		// 게시글 Serial로 동작
		String usrId = (String)session.getAttribute("usrId");
		int serial = Integer.valueOf((String)mpRequest.getParameter("c_serial"));
		String title = mpRequest.getParameter("c_title");
		String contents = mpRequest.getParameter("c_contents");
		String subject = mpRequest.getParameter("subject");
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
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		if(result == true) {
			redirectAttr.addFlashAttribute("result", "true");
		}else {
			redirectAttr.addFlashAttribute("result", "false");
		}
		return "redirect:/community/list?page=1&subject="+subject;
	}
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		// 게시글 삭제요청에 대응하는 엔드포인트
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		String subject = request.getParameter("subject");
		boolean result = cService.deleteCommunity(targetSerial);
		if(result == true) {
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			redirectAttr.addFlashAttribute("subject", subject);
			redirectAttr.addFlashAttribute("result", "true");
		}
		return "redirect:/community/list?page=1&subject="+subject;
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
		 e.printStackTrace();
		 return "/error/404"; }
	


}
