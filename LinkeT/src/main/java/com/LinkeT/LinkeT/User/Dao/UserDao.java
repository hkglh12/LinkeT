package com.LinkeT.LinkeT.User.Dao;

import com.LinkeT.LinkeT.User.User;

public interface UserDao {
	int usrInsert(String usrId, String usrPw, String usrPhone, String usrEmail, String invitationCode);
	User loginUser(String usrId, String usrPw);
	User getUser(String usrId);
}
