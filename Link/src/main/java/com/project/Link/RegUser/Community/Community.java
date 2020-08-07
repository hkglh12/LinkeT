package com.project.Link.RegUser.Community;

import java.util.ArrayList;

import com.project.Link.RegUser.Comment.Comment;
import com.project.Link.RegUser.Posting.Posting;

public class Community extends Posting{
	/* Community POJO는 Posting 객체에 "댓글"기능을 추가한것 
	 * 따라서 상속후 댓글리스트 추가
	 * 
	 * 또한 "사용자"가 작성한 게시글은 "관리자"에 의해
	 * 삭제처리당할수있으므로 해당 멤버변수 추가*/
	private ArrayList<Comment> comments;
	// 유저가 스스로 삭제한게아니라 "강제삭제처리" 당했을경우
	private boolean isBanned;
	// 유저의 "자유게시글"을 삭제한 "관리자" 계정
	private String usrBannedId;
	private String subject;
	private int commentsCount;
	
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	public boolean isBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	public String getUsrBannedId() {
		return usrBannedId;
	}
	public void setUsrBannedId(String usrBannedId) {
		this.usrBannedId = usrBannedId;
	}
}
