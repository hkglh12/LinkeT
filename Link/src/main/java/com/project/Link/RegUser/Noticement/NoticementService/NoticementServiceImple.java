package com.project.Link.RegUser.Noticement.NoticementService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDao;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("noticeservice")
public class NoticementServiceImple implements NoticementService{
	//private였었음
	protected int pagePerBlock = 8;
	protected static final String targetBoard = "noticement";
	protected static final String targetBoardFile = targetBoard+"file";
	protected String prefix = "n_";
	
	@Autowired
	@Qualifier("noticeDao")
	private NoticementDao nDao;
	@Autowired
	private UfileService ufService;
	
	public NoticementServiceImple () {};
	public NoticementDao getnDao() {return nDao;}
	public void setnDao(NoticementDao nDao) {this.nDao = nDao;}


	@Override
	// 삭제처리 되지 않은 공지사항 전체 개수를 가져옵니다.
	public int totalCountNoticements() {
		int totalCount = nDao.getNoticementCount();
		return totalCount;
	}
	// 공지사항중 해당 페이지, 블록개수에 맞춰서 리턴합니다.
	@Override
	public ArrayList<Noticement> listNoticements(int targetPage) {
		ArrayList<Noticement> list = nDao.getListNoticement(targetPage, pagePerBlock);
		return list;
	}
	// 공지사항을 가져오며, 이때 연결된 파일정보도 같이 제공합니다.
	@Override
	public Noticement getNoticement(int targetSerial) {
		Noticement targetNoticement = nDao.getNoticement(targetSerial);
		// 이미 데이터베이스에서 가져왔으므로, 개별적으로 조회수 증가
		nDao.countUp(targetBoard, prefix, targetSerial, targetNoticement.getReadCount()+1);
		// 사용자에게 전달할때, 증가시킨상태로 전달
		targetNoticement.setReadCount(targetNoticement.getReadCount() +1);
		targetNoticement.setuFileList(ufService.uFileGet(targetBoardFile, targetSerial));
		return targetNoticement;
	}
	//파일 코드가 유효한지 확인 
	public boolean validateNoticementFile(String fileCode) {
		String targetBoard = "noticementfile";
		return ufService.uFileValidate(targetBoard, fileCode);
	}
	@Override
	public void fileAccessLog() {
		//Nothing to do
		
	}

}
