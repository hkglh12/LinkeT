package com.project.Link.Admin.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Link.Admin.AdminDao.AdminDao;
import com.project.Link.Commons.User.User;

@Service
public class AdminServiceImple implements AdminService{
	@Autowired
	private AdminDao aDao;
	
	public AdminServiceImple() {}
	public AdminDao getaDao() {
		return aDao;
	}

	public void setaDao(AdminDao aDao) {
		this.aDao = aDao;
	}

	@Override
	public User adminGet(String adminId, String adminPw) {
		User user = aDao.get(adminId, adminPw);
		return user;
	}

}
