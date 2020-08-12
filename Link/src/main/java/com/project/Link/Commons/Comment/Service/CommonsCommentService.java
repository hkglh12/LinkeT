package com.project.Link.Commons.Comment.Service;

import java.util.ArrayList;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Community.Community;

public interface CommonsCommentService {
	// 특정 유저의 댓글개수를 제공
	public int getUserCommentsCount(String usrId);
	// 전체 댓글 개수 제공
	public int totalCountComments(int communitySerial);
	// 게시글 리스트를 제공받아, 각 게시글 당 댓글갯수를 embed 제공
	public ArrayList<Community> totalCountComments(ArrayList<Community> list);
	// 주어진 "community" 번호에 따른 댓글을 리턴하는 메서드
	public ArrayList<Comment> ListComments(int targetSerial, int pageNum);
	// 특정 유저의 마지막 댓글을 리턴
	public Comment getLastUserComment(String usrId);
	// 특정 유저가 작성한 모든 댓글을 리턴
	public ArrayList<Comment> getDirectUserComment(String usrId, int page);
	// 특정 유저의 댓글 개수 리턴
	public int getdirectUsercommentCount(String usrId);
}
