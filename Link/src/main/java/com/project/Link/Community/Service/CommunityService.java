package com.project.Link.Community.Service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.Link.Posting.Posting;

@Service
public interface CommunityService {
	public ArrayList<Posting> ListCommunities(int targetPage);
	public int createCommunity(String usrId, String cmtyTitle, String cmtyContents, int fileCount);
	public Posting getCommunity(int targetSerial);
	public int updateCommunity(int targetSerial, String cmtyTitle, String cmtyContents, int fileCount);
	public int deleteNoticement(int targetSerial);
}
