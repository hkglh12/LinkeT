package com.project.Link.RegUser.Community.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.project.Link.Commons.Community.Service.CommonsCommunityService;

public interface CommunityService extends CommonsCommunityService{
	/* totalCountCommunities(특정 게시글 개수 count),
	   DirectCountCommunities(특정 유저의 게시글 개수 count),
	   GetCommunity (게시글 Read), ListCommunities(게시글 Listing),
	   DirectListCommunities(특정 유저 Listing)
	   상기 기능은 유저-관리자 공통기능이므로 Commons에서 상속
	   */
	
	// 자유게시글 등록
	public boolean createCommunity(String usrId, String title, String contents, List<MultipartFile> uFileList, String subject) throws Exception;
	// 자유게시글 업데이트
	public boolean updateCommunity(String usrId, int serial, String title, String contents,	List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFilelist) throws Exception;
	// 자유게시글 삭제
	public boolean deleteCommunity(int targetSerial);
}
