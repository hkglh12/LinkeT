package com.LinkeT.LinkeT.User.Dao;

import java.sql.Timestamp;

import com.LinkeT.LinkeT.User.User;

public interface UserDao {
	boolean validate(String key, String value);
	int usrInsert(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName, Timestamp signindate);
	User logInUser(String usrId, String usrPw);
	User getUser(String usrId);
	int usrJoinTeam(String usrId, String teamCode);
}
