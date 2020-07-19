package com.LinkeT.LinkeT.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LinkeT.LinkeT.User.User;
import com.LinkeT.LinkeT.User.Dao.UserDaoImple;

//@Component
//@Repository
@Service
public class UserServiceImple implements UserService{
	@Autowired
	UserDaoImple dao;
	
	@Override
	public boolean userRegister(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName) {
		// TODO Auto-generated method stub
		dao.usrInsert(usrId, usrPw, usrPhone, usrEmail,usrName);
		return false;
	}

	@Override
	public User userLoginVerifier(String usrId, String usrPw) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
