package com.project.Link.Admin.AdminDao;

import com.project.Link.RegUser.User.User;

public interface AdminDao {
	public User get(String adminId, String adminPw);
}
