package com.LinkeT.LinkeT.User.Dao;

import com.LinkeT.LinkeT.User.User;

public interface UserDao {
	boolean validate(String key, String value);
	int usrInsert(String usrId, String usrPw, String usrPhone, String usrEmail, String invitationCode);
	User logInUser(String usrId, String usrPw);
	User getUser(String usrId);
	int usrJoinTeam(String usrId, String teamCode);
}
