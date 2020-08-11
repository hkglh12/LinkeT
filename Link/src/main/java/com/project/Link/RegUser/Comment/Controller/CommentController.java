package com.project.Link.RegUser.Comment.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface CommentController {
	public String userDirectListComments(Model model, HttpServletRequest request, HttpSession session);
}
