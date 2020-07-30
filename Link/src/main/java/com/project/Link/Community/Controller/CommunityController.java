package com.project.Link.Community.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.Comment.Comment;

public interface CommunityController {
	//Session컨트롤 필요없음.
	/* public HashMap<String, String> sessionControl(HttpSession session); */
	/* 자유게시판 리스팅 */
	public String ListCommunities(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirecAttr);
	/* 자유게시판 작성 */
	public String PostCommunity(Model model, MultipartHttpServletRequest mpRequest, HttpSession session, RedirectAttributes redirectAttr) throws Exception;
	/* 자유게시판 타게팅 get */
	public String GetCommunity(Model model, HttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시판 타게팅 update*/
	public String UpdateCommunity(Model model, MultipartHttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr)throws Exception;
	/* 자유게시판 타게팅 delete, 유저처리 해야함*/
	public String DeleteCommunity(Model model, HttpServletRequest reqeust, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시글에 등재된 파일 획득*/
	public void getCommunityFile(Model model,HttpServletRequest request, HttpSession session, HttpServletResponse response)throws Exception;
	/* 자유게시판 작성폼 획득*/
	public String getPostTemplate(Model model, HttpServletRequest reqeust, HttpSession session);
	/* 자유게시판 댓글 get(update, delete용) */
	
	/* 자유게시판 댓글 리스팅 */
	/* 리스팅은 사실 controller에 없어도 되겠다.
	 * community get에 동시에 실행되야하니까 service에서 하면 되겠지. */
	/* 자유게시판 댓글 작성 */
	public String PostComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);	
	/* 자유게시판 댓글 업데이트 */
	public String UpdateComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시판 댓글 삭제 */
	public String DeleteComments(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttr);
	/* 자유게시판 개별리스팅*/
	public HashMap<String, ArrayList<Comment>> ListCommentsAjax(@RequestBody HashMap<String,String> ajaxRequest, Model model, HttpServletRequest request, HttpSession session)  throws Exception;
}
