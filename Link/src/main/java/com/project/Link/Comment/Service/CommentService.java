package com.project.Link.Comment.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Comment.Comment;
import com.project.Link.RegUser.Community.Community;

public interface CommentService {
	public int getUserCommentsCount(String usrId);
	public int totalCountComments(int communitySerial);
	public ArrayList<Community> totalCountComments(ArrayList<Community> list);
	public ArrayList<Comment> ListCommunities(int targetSerial, int pageNum);
	
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret);
	public boolean updateComment(int targetSerial, String contents, boolean isSecret);
	public boolean deleteComment(String usrId, int targetSerial);
}
