package com.project.Link.RegUser.Comment.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.RegUser.Comment.Service.CommentService;

@RequestMapping(value="/comment/*")
@Controller
public class CommentControllerImple implements CommentController{
	
	@Autowired
	@Qualifier("UserCommentService")
	private CommentService ccService;
	
	@RequestMapping(value="/direct") // 댓글 계정명으로 검색
	@Override
	public String userDirectListComments(Model model, HttpServletRequest request, HttpSession session) {
		String usrId = request.getParameter("u_id");
		int page = request.getParameter("page") != null ? Integer.valueOf((String)request.getParameter("page"))-1 : 0;
		ArrayList<Comment> list = ccService.getDirectUserComment(usrId, page);
		model.addAttribute("search_target", usrId);
		model.addAttribute("page", page+1);
		model.addAttribute("list", list);
		model.addAttribute("total", ccService.getdirectUsercommentCount(usrId));
		return "/User/comment/board";
	}
	

	@RequestMapping(value="/list", method=RequestMethod.POST)
	@Override
	@ResponseBody
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		// Community 최초 로딩 외에, Paging 처리된 댓글 리스트를 제공하는 엔드포인트
		// Ajax통신을 사용하므로 ResponseBody, RequestBody를 사용함. Jackson-databind 라이브러리 사용
		int targetSerial = ajaxRequest.containsKey("c_serial") == true ? Integer.valueOf(ajaxRequest.get("c_serial")) : 0;
		if(targetSerial == 0) {
			throw new Exception();
		}
		int pageNum = ajaxRequest.containsKey("page_num") == true ? Integer.valueOf(ajaxRequest.get("page_num"))-1: 0;
		ArrayList<Comment> list = ccService.ListComments(targetSerial, pageNum);
		HashMap<String, ArrayList<Comment>> returnInfo = new HashMap<String, ArrayList<Comment>>();
		returnInfo.put("list", list); // 결과값을 Json형태로 리턴하기위해 HashMap 사용
		return returnInfo;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	@Override
	public String PostComments(Model model, HttpServletRequest request, HttpSession session,RedirectAttributes redirectAttr) {
		// 댓글 게시 요청에 응답하는 endpoint
			String usrId = (String)session.getAttribute("usrId");
			int targetSerial = Integer.valueOf((String)request.getParameter("c_serial"));
			String contents = request.getParameter("cc_contents");
			boolean isSecret = request.getParameter("is_secret") != null ? Boolean.valueOf(request.getParameter("is_secret")) : false;
			boolean result = ccService.createComment(usrId, targetSerial, contents, isSecret);
			redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
			redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
			if(result == true) {
				redirectAttr.addFlashAttribute("result", result);
			}
			return "redirect:/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@Override
	public String UpdateComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		// 댓글 수정요청에 대응하는 endpoint
		int targetSerial = Integer.valueOf((String)request.getParameter("cc_serial"));
		String contents = request.getParameter("modi_contents");
		boolean isSecret = request.getParameter("is_secret") != null ? Boolean.valueOf(request.getParameter("is_secret")) : false;
		boolean result = ccService.updateComment(targetSerial, contents, isSecret);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		if(result == true) {
			redirectAttr.addFlashAttribute("result", result);
		}
		return "redirect:/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@Override
	public String DeleteComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr) {
		// 댓글 삭제요청에 응답하는 endpoint
		String usrId = (String)session.getAttribute("usrId");
		int targetSerial = Integer.valueOf((String)request.getParameter("del_serial"));
		boolean result = ccService.deleteComment(usrId, targetSerial);
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		if(result == true) {
			redirectAttr.addFlashAttribute("result", result);
		}
		return "redirect:/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}
	
}
