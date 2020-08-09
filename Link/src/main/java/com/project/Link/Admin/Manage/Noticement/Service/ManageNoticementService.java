package com.project.Link.Admin.Manage.Noticement.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.Link.RegUser.Noticement.NoticementService.NoticementService;

public interface ManageNoticementService extends NoticementService{
	// 공지사항 총 개수 전달, 공지사항 리스팅, 공지사항 GET(조회, read)는 User용 Noticement와 동일하게 동작하므로
	// extend로 해결합니다.
	
	/* 공지사항 등록 */
	public boolean createNoticement(String usrId, String title, String contents, List<MultipartFile> uFileList) throws Exception;
	/* 공지사항 업데이트 */
	public boolean updateNoticement(String usrId, int serial, String title, String contents,List<String> previousFileCodes, List<String> deleteFileCodes,List<MultipartFile> uFileList) throws Exception;
	/* 공지사항삭제 */
	public boolean deleteNoticement(int targetSerial, String usrId);
}
