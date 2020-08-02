package com.project.Link.RegUser.User.UserService;

import com.project.Link.RegUser.User.User;

public interface UserService {
	public String userValidate(String key, String value);
	public boolean userRegist(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName);
	public User userGet(String usrId, String usrPw);
	public User userGet(String usrId);
	public boolean userUpdate(String usrId, String newPw);
	public boolean userDelete(String usrId);
}
