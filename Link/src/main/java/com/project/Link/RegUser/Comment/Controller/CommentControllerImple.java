package com.project.Link.RegUser.Comment.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Link.RegUser.Comment.Service.CommentService;

@RequestMapping(value="/comment/*")
@Controller
public class CommentControllerImple implements CommentController{
	
	@Autowired
	@Qualifier("UserCommentService")
	private CommentService ccService;
	
	@RequestMapping(value="/directListing")
	@Override
	public String userDirectListComments(Model model, HttpServletRequest request, HttpSession session) {
		int target
	}
}
