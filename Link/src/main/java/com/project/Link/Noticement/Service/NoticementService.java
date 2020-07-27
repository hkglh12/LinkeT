package com.project.Link.Noticement.Service;

import java.util.ArrayList;

import com.project.Link.Posting.Posting;

public interface NoticementService {
	/* 공지사항 총 개수 전달 */
	public int totalCountNoticements();
	/* 공지사항 리스팅 */
	public ArrayList<Posting> listNoticements(int targetPage);
	/* 공지사항 등록 */
	public boolean createNoticement(String usrId,String ntcTitle, String ntcContents, int fileCount);
	/* 공지사항 조회 */
	public Posting getNoticement(int targetSerial);
	/* 공지사항 업데이트 */
	//TODO file 등록까지 끝내면 파일등록도 이거에 동기화되야함
	public boolean updateNoticement(int targetSerial, String ntcTitle, String ntcContents, int fileCount);
	/* 공지사항삭제 */
	public boolean deleteNoticement(int targetSerial);
}
