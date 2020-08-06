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

public interface NoticementServicebkup {
	
	/* 공지사항 총 개수 전달 */
	public int totalCountNoticements();
	/* 공지사항 리스팅 */
	public ArrayList<Noticement> listNoticements(int targetPage);
	/* 공지사항 등록 */
	//public boolean createNoticement(String usrId,String ntcTitle, String ntcContents,List<MultipartFile> ufilelist);
	public boolean createNoticement(String usrId, String title, String contents, List<MultipartFile> uFileList) throws Exception;
	/* 공지사항 조회 */
	public Noticement getNoticement(int targetSerial);
	/* 공지사항 업데이트 */
	public boolean updateNoticement(
			String usrId, int serial, String title, String contents,
			List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFileList) throws Exception;
	/* 공지사항삭제 */
	public boolean deleteNoticement(int targetSerial, String usrId);
}
