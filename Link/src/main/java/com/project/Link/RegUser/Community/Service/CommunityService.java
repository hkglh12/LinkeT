package com.project.Link.RegUser.Community.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.project.Link.Commons.Community.Service.CommonsCommunityService;



public interface CommunityService extends CommonsCommunityService{
	// 게시글 총 개수 제공, 특정 User의 게시글 작성 개수 제공은 관리자-사용자 공통기능임
	// 게시글 Listing, 조회는 관리자-사용자 공통기능임
	// 댓글 총 개수, Ajax게시글 Listing은 관리자-사용자 공통기능임
	// 공통기능에 대해서는 Common.Community.Service에서 제공, 이를 상속으로 처리
	
	// 자유게시글 등록
	public boolean createCommunity(String usrId, String title, String contents, List<MultipartFile> uFileList, String subject) throws Exception;
	// 자유게시글 업데이트
	public boolean updateCommunity(String usrId, int serial, String title, String contents,	List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFilelist) throws Exception;
	// 자유게시글 삭제
	public boolean deleteCommunity(int targetSerial);
	
	// 댓글 게시
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret);
	// 댓글 삭제
	public boolean deleteComment(String usrId, int targetSerial);
	// 댓글 갱신
	public boolean updateComment(int targetSerial, String contents, boolean isSecret);
}
