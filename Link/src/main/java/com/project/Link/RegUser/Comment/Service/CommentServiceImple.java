package com.project.Link.RegUser.Comment.Service;

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

import com.project.Link.RegUser.Comment.Comment;
import com.project.Link.RegUser.Comment.Dao.CommentDao;
import com.project.Link.RegUser.Community.Community;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementServiceImple;

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
	public ArrayList<Community> totalCountComments(ArrayList<Community> list) {
		for(Community cm : list) {
			cm.setCommentsCount(ccDao.getTotalCount(cm.getSerial()));
		}
		return list;
		
	}
	@Override
	public ArrayList<Comment> ListCommunities(int targetSerial, int pageNum) {
		ArrayList<Comment> list = ccDao.getListComment(targetSerial, pageNum, pagePerBlock);
		logger.info("listCommunities finished, answer size : " + list.size());
		return list;
	}

	@Override
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret) {
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		return ccDao.createComment(usrId, targetSerial, contents, createDate, isSecret) >=1 ? true : false;
	}

	@Override
	public boolean updateComment(int targetSerial, String contents, boolean isSecret) {
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		return ccDao.updateComment(targetSerial, contents, isSecret, modifyDate) >= 1 ? true : false;
	}

	@Override
	public boolean deleteComment(String usrId, int targetSerial) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		
		return ccDao.deleteComment(targetSerial, usrId, deleteDate) >= 1 ? true : false;
	}
	@Override
	public int getUserCommentsCount(String usrId) {
		return ccDao.getUserCommentCount(usrId);
	}

}
