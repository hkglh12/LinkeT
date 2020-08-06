package com.project.Link.RegUser.Noticement.NoticementService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Posting.Posting;

public interface NoticementService {
	/* 공지사항 총 개수 전달 */
	public int totalCountNoticements();
	/* 공지사항 리스팅 */
	public ArrayList<Noticement> listNoticements(int targetPage);
	/* 공지사항 조회 */
	public Noticement getNoticement(int targetSerial);
}
