package com.project.Link.Admin.AdminDao;

import com.project.Link.Commons.User.User;

public interface AdminDao {
	public User get(String adminId, String adminPw);
}
