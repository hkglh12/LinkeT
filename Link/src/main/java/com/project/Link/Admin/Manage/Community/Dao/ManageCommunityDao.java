package com.project.Link.Admin.Manage.Community.Dao;

import java.sql.Timestamp;

import com.project.Link.Commons.Community.Dao.CommonsCommunityDao;
import com.project.Link.RegUser.Community.Dao.CommunityDao;

public interface ManageCommunityDao extends CommonsCommunityDao{
	public boolean banCommunity(int targetSerial, String usrId, Timestamp deleteDate);
	public boolean bulkBanCommunity(int targetSerial, String usrId, Timestamp deleteDate);
}
