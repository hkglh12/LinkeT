package com.project.Link.Admin.Manage.Community.Service;

import com.project.Link.Commons.Community.Service.CommonsCommunityService;

public interface ManageCommunityService extends CommonsCommunityService {
	/* totalCountCommunities(특정 게시글 개수 count),
	   DirectCountCommunities(특정 유저의 게시글 개수 count),
	   GetCommunity (게시글 Read), ListCommunities(게시글 Listing),
	   DirectListCommunities(특정 유저 Listing)
	   상기 기능은 유저-관리자 공통기능이므로 Commons에서 상속
	   */
	
	// 특정 게시물 Ban
	public boolean banCommunity(int targetSerial, String usrId);
	// 다중 게시글 Ban
	public boolean bulkCommunityBan(String[] list, String usrId);

}
