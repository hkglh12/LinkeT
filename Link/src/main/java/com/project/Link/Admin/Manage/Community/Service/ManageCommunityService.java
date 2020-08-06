package com.project.Link.Admin.Manage.Community.Service;

import com.project.Link.RegUser.Community.Service.CommunityService;

public interface ManageCommunityService extends CommunityService{
	public boolean banCommunity(int targetSerial, String usrId);
	public boolean banComment(int targetSerial, String usrId);
	public boolean bulkCommunityBan(String[] list, String usrId);
}
