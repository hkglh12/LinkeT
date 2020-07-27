package com.project.Link.UserDao;

import java.sql.Timestamp;

import com.project.Link.User.User;

public interface UserDao {
	public boolean validate(String key, String value);
	public int regist(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName, int usrLevel, Timestamp signInDate);
	public User get(String usrId, String usrPw);
	public User get(String usrId);
	public int update(String usrId);
	public int delete(String usrId);
}
