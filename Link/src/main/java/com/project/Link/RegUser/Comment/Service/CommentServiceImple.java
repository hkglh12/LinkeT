package com.project.Link.RegUser.Comment.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Comment.Service.CommonsCommentServiceImple;
import com.project.Link.Commons.Community.Community;
import com.project.Link.RegUser.Comment.Dao.CommentDao;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementServiceImple;

@Service
@Qualifier("UserCommentService")
public class CommentServiceImple extends CommonsCommentServiceImple implements CommentService{

	//private int pagePerBlock=10;
	//private static final String targetBoard = "communitycomments";
	//private String prefix = "n";
	
	@Autowired
	@Qualifier("UserCommentDao")
	private CommentDao ccDao;
	
	public CommentServiceImple() {};
	public CommentDao getCcDao() {return ccDao;}
	public void setCcDao(CommentDao ccDao) {this.ccDao = ccDao;}

	@Override
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret) {
		//댓글 작성
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		return ccDao.createComment(usrId, targetSerial, contents, createDate, isSecret) >=1 ? true : false;
	}

	@Override
	public boolean updateComment(int targetSerial, String contents, boolean isSecret) {
		// 댓글 수정
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		return ccDao.updateComment(targetSerial, contents, isSecret, modifyDate) >= 1 ? true : false;
	}

	@Override
	public boolean deleteComment(String usrId, int targetSerial) {
		// 댓글 삭제
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		return ccDao.deleteComment(targetSerial, usrId, deleteDate) >= 1 ? true : false;
	}
}
