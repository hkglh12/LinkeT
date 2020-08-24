package com.project.Link.RegUser.Comment.Service;

import com.project.Link.Commons.Comment.Service.CommonsCommentService;

public interface CommentService extends CommonsCommentService {
	
	// 특정유저 댓글개수, 전체댓글개수
	// Ajax 댓글 get, 게시글 댓글
	// 게시글 리스팅 시에 댓글개수 embed, 
	// 마지막댓글, 특정유저 모든댓글 은 공통기능이므로 상속으로 처리
	
	
	// 댓글 작성
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret);
	// 댓글 수정
	public boolean updateComment(int targetSerial, String contents, boolean isSecret);
	// 댓글 삭제
	public boolean deleteComment(String usrId, int targetSerial);
}
