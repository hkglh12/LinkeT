package com.project.Link.Commons.Comment.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Commons.Comment.Dao.CommonsCommentDao;
import com.project.Link.Commons.Community.Community;

@Service
@Qualifier("CommonsCommentService")
public class CommonsCommentServiceImple implements CommonsCommentService {
	
	protected int pagePerBlock=10;
	protected static final String targetBoard = "communitycomments";
	protected String prefix = "n";
	
	@Autowired
	@Qualifier("CommonsCommentDao")
	private CommonsCommentDao ccDao;
	
	public CommonsCommentDao getCcDao() {
		return ccDao;
	}

	public void setCcDao(CommonsCommentDao ccDao) {
		this.ccDao = ccDao;
	}
	
	@Override
	public int getUserCommentsCount(String usrId) {
		// 특정 유저의 댓글 개수를 리턴
		return ccDao.getUserCommentCount(usrId);
	}
	@Override
	public ArrayList<Comment> getDirectUserComment(String usrId , int page){
		ArrayList<Comment> list = ccDao.getDirectUserComment(usrId, page, pagePerBlock);
		
		for(Comment c : list) {
			if(c.getContents().length() >= 21) {
				System.out.println(c.getContents().length());
				c.setContents(c.getContents().substring(0, 20)+"...");
			}
		}
		//특정 유저가 작성한 리스트 리턴
		return list;
	}

	@Override
	public int totalCountComments(int communitySerial) {
		//특정 게시글에 대한 총 댓글 개수를 리턴
		return ccDao.getTotalCount(communitySerial);
	}
	@Override
	public ArrayList<Community> totalCountComments(ArrayList<Community> list) {
		// 게시글 ArrLIst에 대해 각각 총 댓글 개수를 embed
		for(Community cm : list) {
			cm.setCommentsCount(ccDao.getTotalCount(cm.getSerial()));
		}
		return list;	
	}
	@Override
	public ArrayList<Comment> ListComments(int targetSerial, int pageNum) {
		// 최초 게시글 접속 시에, 댓글 1페이지 제공
		ArrayList<Comment> list = ccDao.getListComment(targetSerial, pageNum, pagePerBlock);
		return list;
	}

	@Override
	public Comment getLastUserComment(String usrId) {
		Comment result = ccDao.getLastUserComment(usrId);
		if( result != null && result.getContents().length() >= 6) {
			result.setContents(result.getContents().substring(0, 5)+"...");
		}
		return result;
	}
	
	@Override
	public int getdirectUsercommentCount(String usrId) {
		return ccDao.getUserCommentCount(usrId);
	}
}
