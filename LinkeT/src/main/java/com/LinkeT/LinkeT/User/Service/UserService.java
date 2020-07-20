package com.LinkeT.LinkeT.User.Service;

import com.LinkeT.LinkeT.User.User;

public interface UserService {
	
	public boolean userRegister(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName);
	public User loginUser(String usrId, String usrPw);
	public User getUser(String usrId);
}
