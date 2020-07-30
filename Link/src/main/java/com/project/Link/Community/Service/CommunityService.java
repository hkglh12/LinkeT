package com.project.Link.Community.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Comment.Comment;
import com.project.Link.Community.Community;

@Service
public interface CommunityService {
	// 자유게시글 총 갯수 리턴 (삭제안해썩나, 벤당하지 않은 것만)
	public int totalCountCommunities();
	// 자유게시글 리스팅
	public ArrayList<Community> ListCommunities(int targetPage);
	// 자유게시글 등록
	public boolean createCommunity(String usrId, String title, String contents, List<MultipartFile> uFileList) throws Exception;
	// 자유게시글 조회
	public Community getCommunity(int targetSerial);
	// 자유게시글 업데이트
	public boolean updateCommunity(String usrId, int serial, String title, String contents,
			List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFilelist) throws Exception;
	// 자유게시글 삭제
	public boolean deleteCommunity(int targetSerial);
	
	public int getCommentTotalCount(int communitySerial);
	public ArrayList<Comment> ListCommentsAjax(int targetSerial, int pageNum);
	public boolean createComment(String usrId, int targetSerial, String contents, boolean isSecret);
	public boolean deleteComment(String usrId, int targetSerial);
	public boolean updateComment(int targetSerial, String contents, boolean isSecret);

}
