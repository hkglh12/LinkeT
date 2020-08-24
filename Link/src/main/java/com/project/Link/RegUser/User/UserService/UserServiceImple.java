package com.project.Link.RegUser.User.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Commons.User.User;
import com.project.Link.Commons.User.Service.CommonsUserServiceImple;
import com.project.Link.RegUser.Comment.Service.CommentService;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.RegUser.User.UserDao.UserDao;

@Service
@Qualifier("UserService")
public class UserServiceImple extends CommonsUserServiceImple implements UserService{
	@Autowired
	@Qualifier("UserDao")
	private UserDao uDao;
	@Autowired
	@Qualifier("UserCommunityService")
	private CommunityService cService;
	@Autowired
	@Qualifier("UserCommentService")
	private CommentService ccService;
	public UserServiceImple() {}
	public UserDao getuDao() {
		return uDao;
	}

	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Override
	public String userValidate(String key, String value) {
		String result = uDao.validate(key,value) == false? "accept" : "deny";
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
	public boolean userUpdate(String usrId, String newPw) {
		boolean result = uDao.update(usrId, newPw);
		return result;
	}

	@Override
	public boolean userDelete(String usrId) {
		Timestamp signOutDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = uDao.delete(usrId, signOutDate);
		return result;
	}

}
