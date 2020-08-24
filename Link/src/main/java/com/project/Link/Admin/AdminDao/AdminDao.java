package com.project.Link.Admin.AdminDao;

import com.project.Link.Commons.User.User;

public interface AdminDao {
	// 관리자 로그인은 level을 dao의 select 단에서 점검
	public User get(String adminId, String adminPw);
}
