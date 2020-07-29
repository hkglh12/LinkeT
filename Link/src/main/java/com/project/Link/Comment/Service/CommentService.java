package com.project.Link.Comment.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Comment.Comment;

public interface CommentService {
	public int totalCountComments();
	public ArrayList<Comment> ListCommunities(int targetSerial);
	public boolean createComment(HttpServletRequest request, HttpSession session);
	public boolean updateComment(HttpSession session,HttpServletRequest request);
	public boolean deleteCommunity(HttpSession session,HttpServletRequest request);
}
