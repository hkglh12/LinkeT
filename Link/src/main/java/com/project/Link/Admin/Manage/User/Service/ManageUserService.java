package com.project.Link.Admin.Manage.User.Service;

import java.util.ArrayList;

import com.project.Link.Commons.User.User;
import com.project.Link.Commons.User.Service.CommonsUserService;

public interface ManageUserService extends CommonsUserService{
	// 조건 하 유저수. Paging 때문에 제공하는 부분
	public int getCountUser(String mainCategory, String subCategory ,String searchTarget);
	// 실제 리스트를 가져옵니다. (조건 하)
	public ArrayList<User> getUsers(String category, int page, String search_target, String subCategory);
	// 유저를 ban 합니다.
	public boolean banUsers(String[] targetList, String usrId);
}
