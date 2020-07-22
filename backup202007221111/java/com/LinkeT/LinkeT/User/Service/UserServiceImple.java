package com.LinkeT.LinkeT.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LinkeT.LinkeT.User.User;
import com.LinkeT.LinkeT.User.Dao.UserDao;
import com.LinkeT.LinkeT.User.Dao.UserDaoImple;

//@Component
//@Repository
@Service
public class UserServiceImple implements UserService{
	
	@Autowired
	UserDaoImple dao;
	
	@Override
	public boolean userRegister(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName) {
		// 데이터베이스 접속, transaction 결과를 service에서 성공/실패 로 변환하여 리턴
		boolean result = dao.usrInsert(usrId, usrPw, usrPhone, usrEmail,usrName) >=1 ? true:false;
		
		return result;
	}

	@Override
	public User loginUser(String usrId, String usrPw) {
		// TODO Auto-generated method stub
		User user = dao.loginUser(usrId,  usrPw);
		return user;
	}
	@Override
	public User getUser(String usrId) {
		User user = dao.getUser(usrId);
		return user;
	}

	@Override
	public int joinTeamUser(String usrId, String teamCode) {
		boolean result = dao.usrJoinTeam(usrId, teamCode) >=1 ? true : false;
		
		return 0;
	}

	

}
