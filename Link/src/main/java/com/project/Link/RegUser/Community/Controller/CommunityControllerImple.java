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

import com.project.Link.Commons.Community.Community;
import com.project.Link.RegUser.Comment.Service.CommentService;
import com.project.Link.RegUser.Community.Service.CommunityService;
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
	
	// 자유게시글 입력 양식 제공, 특정 게시판에 대한 입력임을 attribute로 제공
	@RequestMapping(value= "/form", method=RequestMethod.GET)
	@Override
	public String getPostTemplate(Model model, HttpServletRequest request, HttpSession session) {
		String subject = request.getParameter("subject") == null ? "java" : request.getParameter("subject");
		model.addAttribute("subject", subject);
		return "/User/community/post";		
	}
	
	// 자유게시글 업로드
	@RequestMapping(value="/post", method = RequestMethod.POST)
	@Override
	public String PostCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		String usrId = (String)session.getAttribute("usrId");
		String title = mpRequest.getParameter("c_title");
		String contents = mpRequest.getParameter("c_contents");
		String subject = mpRequest.getParameter("c_subject");
		// 파일 리스트를 업로드하기위해 추출
		List<MultipartFile> uFileList = mpRequest.getFiles("u_files");
		boolean result = cService.createCommunity(usrId, title, contents, uFileList, subject);
		if(result == true) {
			redirectAttr.addFlashAttribute("result", "true"); // 성공했다면 결과값 JSP로 리턴
		}
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		return "redirect:/community/list?subject="+subject+"&page=1";
	}
	// 자유게시글 조회
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@Override
	public String GetCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr) {
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = cService.getCommunity(targetSerial);
		// 최초 접근시, 댓글 리스트의 page는 1페이지 >> DB접근을 위해 0페이지로 변조, service로 내림
		int pageNum=0;
		community.setComments(ccService.ListComments(community.getSerial(),pageNum));
		model.addAttribute("total_comment", ccService.totalCountComments(targetSerial));
		model.addAttribute("community",community);
		return "/User/community/read";
	}
	//자유 게시글 업데이트 양식과 기존의 내용을 제공. 단, 리턴페이지가 다르되 동작내용은 GET과 동일
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String getUpdateCommunity(Model model, HttpServletRequest request, HttpSession session) {
		// 게시글 수정 요청을 위해, 정보와 form 요청에 응답하는 엔드포인트
		int targetSerial = Integer.valueOf(request.getParameter("c_serial"));
		Community community = cService.getCommunity(targetSerial);
		model.addAttribute("community",community);
		return "/User/community/updateForm";
	}
	// 자유게시글 Update 실행.
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Override
	public String UpdateCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception {
		// 게시글 Serial로 동작
		String usrId = (String)session.getAttribute("usrId");
		int serial = Integer.valueOf((String)mpRequest.getParameter("c_serial"));
		String title = mpRequest.getParameter("c_title");
		String contents = mpRequest.getParameter("c_contents");
		String subject = mpRequest.getParameter("subject");
		// 파일 변동내역 확인 (리스트 추출)
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
		//추출 종료, service로 넘겨서 실행
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
	// 게시글 삭제
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@Override
	public String DeleteCommunity(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
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
	// 게시글 리스팅
	@RequestMapping(value={"/list","/"}, method = RequestMethod.GET)
	@Override
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session) {
		// 자유게시판 글 목록을 read, 한개의 테이블로 java, jsp, spring 게시글을 모아두므로, subject 컬럼이 필요함.
		int targetPage = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		String searchCategory = request.getParameter("search_category") == null ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null ? null : request.getParameter("search_target");
		String subject = request.getParameter("subject") == null? "java" : request.getParameter("subject");
		ArrayList<Community> list = cService.ListCommunities(targetPage, searchCategory, searchTarget, subject);
		list = ccService.totalCountComments(list);
		int total = cService.totalCountCommunities(searchCategory, searchTarget, subject);
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		model.addAttribute("page", targetPage);
		if(searchCategory != null) {
			model.addAttribute("search_category", searchCategory);
			model.addAttribute("search_target", searchTarget);
			model.addAttribute("subject", subject);
		}
		return "/User/community/board";
	}
	// 특정 유저가 작성한 게시글을 특정 조건에 더하여 (2차검색) 리턴해주는 페이지입니다.
	@RequestMapping(value="/directlist", method=RequestMethod.GET)
	@Override
	public String DirectListCommunities(Model model, HttpServletRequest request, HttpSession session) {
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
		params.put("deletedate", "is null");
		
		ArrayList<Community> list =  cService.DirectListCommunities(targetPage, params);
		list = ccService.totalCountComments(list);
		int	total = cService.directCountCommunities(params);
		model.addAttribute("total", total);
		model.addAttribute("communitylist",list);
		if(searchCategories != null) {
			model.addAttribute("search_category", searchCategories);
			model.addAttribute("search_target", searchTargets);
			for(String str : searchTargets) {
				System.out.println(str);
			}
			model.addAttribute("subject", subject);
		}
		return "/User/community/board";
	}
	// 올라와있는 파일을 다운로드 합니다.
	@RequestMapping(value="/download", method=RequestMethod.GET)
	@Override
	public void getCommunityFile(Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws Exception {
		try {
			String fileCode = request.getParameter("fileCode");
			if(cService.validateCommunityFile(fileCode)) {
			File file = new File(cFilePath+fileCode);
			if(file.exists()) {
				cService.fileAccessLog();
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
	
	 @ExceptionHandler(value=Exception.class)
	 public String handleDemoException(Exception e) { 
		 e.printStackTrace();
		 return "/error/404"; }
}
