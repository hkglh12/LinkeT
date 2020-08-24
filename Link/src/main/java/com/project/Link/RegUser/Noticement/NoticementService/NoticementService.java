package com.project.Link.RegUser.Noticement.NoticementService;

import java.util.ArrayList;
import com.project.Link.RegUser.Noticement.Noticement;

public interface NoticementService { // 유저측, 관리자측은 이걸 그대로 상속받아서 사용함.
	// 공통 기능의 개념이 아니라 완전히 똑같아서 Commons로 빼지 않음
	/* 공지사항 총 개수 전달 */
	public int totalCountNoticements();
	/* 공지사항 리스팅 */
	public ArrayList<Noticement> listNoticements(int targetPage);
	/* 공지사항 조회 */
	public Noticement getNoticement(int targetSerial);
	// 공지사항에 첨부되어있는 파일코드가 유효한지 식별하는 코드
	public boolean validateNoticementFile(String fileCode);
	
	public void fileAccessLog();
}
