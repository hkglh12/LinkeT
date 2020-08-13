package com.project.Link.Admin.Manage.Comment.Controller;

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

import com.project.Link.Admin.Manage.Comment.Service.ManageCommentService;
import com.project.Link.Commons.Comment.Comment;

@RequestMapping(value="/admin/manage/comment/*")
@Controller
public class ManageCommentControllerImple implements ManageCommentController {
	
	@Autowired
	@Qualifier("ManageCommentService")
	private ManageCommentService mccService;
	
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
		ArrayList<Comment> list = mccService.ListComments(targetSerial, pageNum);
		HashMap<String, ArrayList<Comment>> returnInfo = new HashMap<String, ArrayList<Comment>>();
		returnInfo.put("list", list); // 결과값을 Json형태로 리턴하기위해 HashMap 사용
		return returnInfo;
	}

	@RequestMapping(value="/ban", method=RequestMethod.POST)
	@Override
	public String BanComment(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr) {
		// 특정 댓글을 벤 합니다.
		String usrId = (String)session.getAttribute("usrId");
		int targetSerial = request.getParameter("del_serial") != null ? Integer.valueOf((String)request.getParameter("del_serial")) : -1;
		boolean result = false;
		if(targetSerial != -1) {
			result = mccService.banComment(targetSerial, usrId);
		}else{
			return "failed";
		}
		redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
		redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
		if(result == true) {
			redirectAttr.addFlashAttribute("result", result);
		}
		return "redirect:/admin/manage/community/get?c_serial="+Integer.valueOf((String)request.getParameter("c_serial"));
	}

	@RequestMapping(value="/direct", method=RequestMethod.GET)
	@Override
	//특정 유저가 작성한 댓글 리스트를 제공하는 엔드포인트입니다
	public String getUserDirectComments(Model model, HttpServletRequest request, HttpSession session,	RedirectAttributes redirectAttr) {
		String usrId = request.getParameter("u_id");
		int page = request.getParameter("page") != null ? Integer.valueOf((String)request.getParameter("page"))-1 : 0;
		ArrayList<Comment> list = mccService.getDirectUserComment(usrId, page);
		model.addAttribute("search_target", usrId);

		model.addAttribute("page", page);
		model.addAttribute("list", list);
		model.addAttribute("total", mccService.getdirectUsercommentCount(usrId));
		return "/Admin/manage/comment/board";
	}

}
