package com.project.Link.RegUser.User.UserDao;

import java.sql.Timestamp;

import com.project.Link.RegUser.User.User;

public interface UserDao {
	public boolean validate(String key, String value);
	public int regist(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName, int usrLevel, Timestamp signInDate);
	public User get(String usrId, String usrPw);
	public User get(String usrId);
	public boolean update(String usrId, String newPw);
	public boolean delete(String usrId, Timestamp signOutDate);
}
