package com.project.Link.Commons.Community.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Community.Community;

public interface CommonsCommunityService{
	// 일반사용자의"User Profile", 관리자의 "UserManagement"에서  작성한 게시글 갯수를 조회할때 사용
	public int directCountCommunities(HashMap<String, Object> params);
	// 일반 사용자가 자유게시글 list에 접근할때 paging 처리를 위해 총 게시글 갯수를 제공
	public int totalCountCommunities(String searchCategory, String searchTarget, String subject);
	// 자유게시글 리스팅
	public ArrayList<Community> ListCommunities(int targetPage, String searchCategory, String searchTarget, String community_subject);
	// 자유게시글 2차조회
	public ArrayList<Community> DirectListCommunities(int targetPage, HashMap<String, Object> params);
	// 자유게시글 조회
	public Community getCommunity(int targetSerial);
	// 특정 게시글에 대해 총 댓글 개수를 출력 (CommentService와는 다르게 총 게시글 Listing할때 사용됨)
//	public int getCommentTotalCount(int communitySerial);
	 // 특정 게시글에 대해 댓글 리스트를 Ajax통신처리
//	public ArrayList<Comment> ListCommentsAjax(int targetSerial, int pageNum);
	public Community getLastUserCommunity(String usrId);
}
