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
}
