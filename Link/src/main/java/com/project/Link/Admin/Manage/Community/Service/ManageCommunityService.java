package com.project.Link.Admin.Manage.Community.Service;

import java.util.ArrayList;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Community.Service.CommonsCommunityService;
import com.project.Link.RegUser.Community.Service.CommunityService;

public interface ManageCommunityService extends CommonsCommunityService {
	// 사용자 게시글 갯수확인, 총 게시글 갯수 확인
	// 자유게시글 리스팅, 조회, 특정게시글의 댓글개수출력,
	// 특정 게시글 ajax 댓글 get은 유저-관리자 공통기능이므로
	// commonsCommunityService 상속처리
	
	// 특정 게시물 Ban
	public boolean banCommunity(int targetSerial, String usrId);
	// 다중 게시글 Ban
	public boolean bulkCommunityBan(String[] list, String usrId);

}
