package com.project.Link.Admin.AdminService;

import com.project.Link.Commons.User.User;

public interface AdminService {
	// 관리자 로그인 메서드
	public User adminGet(String usrId, String usrPw);
}
