package com.project.Link.Community.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.Link.Community.Dao.CommunityDao;
import com.project.Link.Noticement.Controller.NoticementControllerImple;
import com.project.Link.Posting.Posting;

@Service
public class CommunityServiceImple implements CommunityService{
	private int pagePerBlock=10;
	
	@Autowired
	private CommunityDao cDao; 
	
	public CommunityServiceImple() {}

	@Override
	public ArrayList<Posting> ListCommunities(int targetPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createCommunity(String usrId, String cmtyTitle, String cmtyContents, int fileCount) {
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		int result = cDao.create(usrId, cmtyTitle, cmtyContents, fileCount, createDate);
		return result;
	}

	@Override
	public Posting getCommunity(int targetSerial) {
		Posting community = cDao.get(targetSerial);
		return community;
	}

	@Override
	public int updateCommunity(int targetSerial, String cmtyTitle, String cmtyContents, int fileCount) {
		Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
		int result = cDao.update(targetSerial, cmtyTitle, cmtyContents, fileCount, modifyDate);
		return result;
	}

	@Override
	public int deleteNoticement(int targetSerial) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		int result = cDao.delete(targetSerial, deleteDate);
		return result;
	}
}


