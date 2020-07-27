package com.project.Link.Community.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.Posting.Posting;

public interface CommunityDao {

	public int create(String usrId, String cmtyTitle, String cmtyContents, int fileCount, Timestamp createDate);
	public ArrayList<Posting> list(int page);
	public Posting get(int targetSerial);
	public int update(int targetSerial, String cmtyTitle, String cmtyContents, int fileCount, Timestamp modifyDate);
	public int delete(int targetSerial, Timestamp deleteDate);
}
