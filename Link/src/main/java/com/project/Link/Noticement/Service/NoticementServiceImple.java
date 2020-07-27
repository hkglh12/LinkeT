package com.project.Link.Noticement.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Link.Noticement.Dao.NoticementDao;
import com.project.Link.Posting.Posting;
import com.project.Link.Posting.Dao.PostingDaoImple;

@Service
public class NoticementServiceImple implements NoticementService{
	private int pagePerBlock = 8;
	private String targetBoard = "noticement";
	private String prefix = "n";
	@Autowired
	private NoticementDao nDao;
	@Autowired
	private PostingDaoImple pDao;
	
	public NoticementServiceImple () {};
	
	public NoticementDao getnDao() {
		return nDao;
	}

	public void setnDao(NoticementDao nDao) {
		this.nDao = nDao;
	}

	@Override
	public int totalCountNoticements() {
		int totalCount = nDao.total();
		return totalCount;
	}
	
	@Override
	public ArrayList<Posting> listNoticements(int targetPage) {
		ArrayList<Posting> list = nDao.list(targetPage, pagePerBlock);
		return list;
	}
	
	@Override
	public boolean createNoticement(String usrId, String ntcTitle, String ntcContents, int fileCount) {
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = nDao.create(usrId, ntcTitle, ntcContents, fileCount, createDate) >=1 ? true :false;
		
		return result;
	}

	@Override
	public Posting getNoticement(int targetSerial) {
		/* 조회수 올려주는 포스팅도 해야함 posting에 service 열자*/
		boolean upcount = pDao.update(targetBoard, prefix, pDao.get(targetBoard, prefix, targetSerial)+1, targetSerial) >= 1 ? true : false;
		
		Posting noticement = nDao.get(targetSerial);
		return noticement;
	}

	@Override
	public boolean updateNoticement(int targetSerial, String ntcTitle, String ntcContents, int fileCount) {
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = nDao.update(targetSerial, ntcTitle, ntcContents, fileCount, modifyDate) >= 1 ? true : false;
		return result;
	}

	@Override
	public boolean deleteNoticement(int targetSerial) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = nDao.delete(targetSerial, deleteDate) >= 1 ? true : false;
		return result;
	}

	

}
