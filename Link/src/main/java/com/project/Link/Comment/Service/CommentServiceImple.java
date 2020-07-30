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
	public int totalCountComments(int communitySerial) {
		logger.info("totalPage called");
		int totalCount = ccDao.getTotalCount(communitySerial);
		
		return totalCount;
	}

	@Override
	public ArrayList<Comment> ListCommunities(int targetSerial, int pageNum) {
		logger.info("listCommunities called");
		/*
		 * int targetPage = request.getParameter("page") == null ? 0 :
		 * Integer.parseInt(request.getParameter("page"))-1;
		 */	
		System.out.println(targetSerial);
		ArrayList<Comment> list = ccDao.getListComment(targetSerial, pageNum, pagePerBlock);
		logger.info("listCommunities finished, answer size : " + list.size());
		return list;
	}

	@Override
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret) {
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		ccDao.createComment(usrId, targetSerial, contents, createDate, isSecret);
		return true;
	}

	@Override
	public boolean updateComment(int targetSerial, String contents, boolean isSecret) {
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		ccDao.updateComment(targetSerial, contents, modifyDate);
		return false;
	}

	@Override
	public boolean deleteCommunity(String usrId, int targetSerial) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		ccDao.deleteComment(targetSerial, usrId, deleteDate);
		return false;
	}

}
