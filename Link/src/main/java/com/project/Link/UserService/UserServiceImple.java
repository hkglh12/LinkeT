package com.project.Link.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Link.User.User;
import com.project.Link.UserDao.UserDao;

@Service
public class UserServiceImple implements UserService{
	@Autowired
	private UserDao uDao;
	
	public UserServiceImple() {}
	public UserDao getuDao() {
		return uDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Override
	public String userValidate(String key, String value) {
		System.out.println("userValidate called");
		String result = uDao.validate(key,value) == false? "accept" : "deny";
		System.out.println("result : " + result);
		return result;
	}

	@Override
	public boolean userRegist(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName) {
		Timestamp signInDate = Timestamp.valueOf(LocalDateTime.now());
		int usrLevel = 1;
		boolean result = uDao.regist(usrId, usrPw, usrPhone, usrEmail, usrName, usrLevel,signInDate) >= 1 ? true : false;
		return result;
	}

	@Override
	/* 로그인으로 쓰임 */
	public User userGet(String usrId, String usrPw) {
		User user = uDao.get(usrId, usrPw);
		return user;
	}

	@Override
	/* 회원정보가져오기로 쓰임 */
	public User userGet(String usrId) {
		User user = uDao.get(usrId);
		return user;
	}

	@Override
	public boolean userUpdate(String usrId) {
		
		return false;
	}

	@Override
	public boolean userDelete(String usrId) {
		// TODO Auto-generated method stub
		return false;
	}

}
