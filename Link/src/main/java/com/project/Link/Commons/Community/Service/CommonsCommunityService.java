package com.project.Link.Commons.Community.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.Link.Commons.Community.Community;

public interface CommonsCommunityService{
	// 일반 사용자가 자유게시글 list에 접근할때 paging 처리를 위해 총 게시글 갯수를 제공
	public int totalCountCommunities(String searchCategory, String searchTarget, String subject);
	// 일반사용자의"User Profile", 관리자의 "UserManagement"에서  작성한 게시글 갯수를 조회할때 사용
	public int directCountCommunities(HashMap<String, Object> params);
	
	// 자유게시글 조회
	public Community getCommunity(int targetSerial);
	// 특정 유저가 마지막으로 작성한 글 제목, 날짜를 리턴
	public Community getLastUserCommunity(String usrId);
	// 자유게시글 리스팅
	public ArrayList<Community> ListCommunities(int targetPage, String searchCategory, String searchTarget, String community_subject);
	// 자유게시글 2차조회 ( 특정 유저의 특정 게시글 제목)
	public ArrayList<Community> DirectListCommunities(int targetPage, HashMap<String, Object> params);
	// 파일 유효성 검증
	public boolean validateCommunityFile(String fileCode);
}
