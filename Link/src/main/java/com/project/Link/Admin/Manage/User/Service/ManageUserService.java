package com.project.Link.Admin.Manage.User.Service;

import java.util.ArrayList;

import com.project.Link.Commons.User.User;
import com.project.Link.Commons.User.Service.CommonsUserService;

public interface ManageUserService extends CommonsUserService{
	public int getCountUser(String mainCategory, String subCategory ,String searchTarget);
	public ArrayList<User> getUsers(String category, int page, String search_target, String subCategory);
	public boolean banUsers(String[] targetList, String usrId);
}
