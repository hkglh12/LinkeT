package com.project.Link.RegUser.Noticement.NoticementService;

import java.util.ArrayList;
import com.project.Link.RegUser.Noticement.Noticement;

public interface NoticementService {
	/* 공지사항 총 개수 전달 */
	public int totalCountNoticements();
	/* 공지사항 리스팅 */
	public ArrayList<Noticement> listNoticements(int targetPage);
	/* 공지사항 조회 */
	public Noticement getNoticement(int targetSerial);
	// 공지사항에 첨부되어있는 파일코드가 유효한지 식별하는 코드
	public boolean validateNoticementFile(String fileCode);
}
