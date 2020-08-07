package com.project.Link.RegUser.Noticement.NoticementService;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDao;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDaoImple;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.Ufile.Dao.UfileDao;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("noticeservice")
public class NoticementServiceImple implements NoticementService{
	private static final Logger logger = LoggerFactory.getLogger(NoticementServiceImple.class);
	private int pagePerBlock = 8;
	private static final String targetBoard = "noticement";
	private static final String targetBoardFile = targetBoard+"file";
	private static final String nFilePath = "C:\\temp\\" + targetBoard + "\\";
	private String prefix = "n_";
	
	@Autowired
	@Qualifier("noticeDao")
	private NoticementDao nDao;
	@Autowired
	private UfileService ufService;
	
	public NoticementServiceImple () {};
	public NoticementDao getnDao() {return nDao;}
	public void setnDao(NoticementDao nDao) {this.nDao = nDao;}

	// 전체 공지사항 개수를 리턴합니다.
	// JSP 파일 중 하단 page block에 사용됩니다.
	@Override
	public int totalCountNoticements() {
		int totalCount = nDao.getNoticementCount(targetBoard, prefix);
		return totalCount;
	}
	// 공지사항중 해당 페이지, 블록개수에 맞춰서 리턴합니다.
	@Override
	public ArrayList<Noticement> listNoticements(int targetPage) {
		logger.info("			ServiceLevelCalled ::::::: listNoticements");
		ArrayList<Noticement> list = nDao.getListNoticement(targetPage,pagePerBlock);
		return list;
	}
	// 공지사항을 가져오며, 이때 연결된 파일정보도 같이 제공합니다.
	@Override
	public Noticement getNoticement(int targetSerial) {
		logger.info("			ServiceLevelCalled ::::::: getNoticement called");
		Noticement targetNoticement = nDao.getNoticement(targetSerial);
		nDao.countUp(targetBoard, prefix, targetSerial, targetNoticement.getReadCount()+1);
		targetNoticement.setReadCount(targetNoticement.getReadCount() +1);
		targetNoticement.setuFileList(ufService.uFileGet(targetBoardFile, targetSerial));
		
		return targetNoticement;
	}

	

	

	

}
