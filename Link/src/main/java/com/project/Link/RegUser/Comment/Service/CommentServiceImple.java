package com.project.Link.RegUser.Comment.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Commons.Comment.Service.CommonsCommentServiceImple;
import com.project.Link.RegUser.Comment.Dao.CommentDao;

@Service
@Qualifier("UserCommentService")
public class CommentServiceImple extends CommonsCommentServiceImple implements CommentService{
	// 데이터베이스 접근변수는 모두 protected로 상속받아서 사용
	// 특정유저 댓글개수, 전체댓글개수
	// Ajax 댓글 get, 게시글 댓글
	// 게시글 리스팅 시에 댓글개수 embed, 
	// 마지막댓글, 특정유저 모든댓글 은 공통기능이므로 상속으로 처리
	
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
