package com.project.Link.Comment.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Comment.Comment;
import com.project.Link.Comment.Dao.CommentDao;
import com.project.Link.Noticement.Service.NoticementServiceImple;

@Service
public class CommentServiceImple implements CommentService{
	private static final Logger logger = LoggerFactory.getLogger(NoticementServiceImple.class);
	private int pagePerBlock=10;
	private static final String targetBoard = "communitycomments";
	private String prefix = "n";
	
	@Autowired
	private CommentDao ccDao;
	
	public CommentServiceImple() {};
	public CommentDao getCcDao() {return ccDao;}
	public void setCcDao(CommentDao ccDao) {this.ccDao = ccDao;}


	@Override
	public int totalCountComments() {
		logger.info("totalPage called");
		int totalCount = ccDao.getTotalCount();
		logger.info("totalpage get result : " + totalCount);
		return totalCount;
	}

	@Override
	public ArrayList<Comment> ListCommunities(int targetSerial) {
		logger.info("listCommunities called");
		/*
		 * int targetPage = request.getParameter("page") == null ? 0 :
		 * Integer.parseInt(request.getParameter("page"))-1;
		 */	
		int targetPage = 0;
		ArrayList<Comment> list = ccDao.getListComment(targetSerial, targetPage, pagePerBlock);
		logger.info("listCommunities finished, answer size : " + list.size());
		return list;
	}

	@Override
	public boolean createComment(HttpServletRequest request, HttpSession session) {
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		String usrId = (String)session.getAttribute("usrId");
		String contents = request.getParameter("cc_contents");
		int communitySerial = Integer.valueOf(request.getParameter("c_serial"));
		boolean isSecret = false;
		ccDao.createComment(communitySerial, usrId, contents, createDate, isSecret);
		return true;
	}

	@Override
	public boolean updateComment(HttpSession session, HttpServletRequest request) {
		int serial = Integer.valueOf(request.getParameter("cc_serial"));
		String contents = request.getParameter("cc_contents");
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		ccDao.updateComment(serial, contents, modifyDate);
		return false;
	}

	@Override
	public boolean deleteCommunity(HttpSession session, HttpServletRequest request) {
		int serial = Integer.valueOf(request.getParameter("cc_serial"));
		String usrId = (String) session.getAttribute("usrId");
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		ccDao.deleteComment(serial, usrId, deleteDate);
		return false;
	}

}
