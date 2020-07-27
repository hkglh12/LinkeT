package com.project.Link.Noticement.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Posting.Posting;

public interface NoticementService {
	
	/* 공지사항 총 개수 전달 */
	public int totalCountNoticements();
	/* 공지사항 리스팅 */
	public ArrayList<Posting> listNoticements(HttpServletRequest request);
	/* 공지사항 등록 */
	//public boolean createNoticement(String usrId,String ntcTitle, String ntcContents,List<MultipartFile> ufilelist);
	public boolean createNoticement(MultipartHttpServletRequest mpRequest, HttpSession session) throws Exception;
	/* 공지사항 조회 */
	public Posting getNoticement(HttpServletRequest request);
	/* 공지사항 업데이트 */
	//TODO file 등록까지 끝내면 파일등록도 이거에 동기화되야함
	public boolean updateNoticement(HttpSession session,MultipartHttpServletRequest mprequest) throws Exception;
	/* 공지사항삭제 */
	public boolean deleteNoticement(HttpSession session,HttpServletRequest request);
}
