package com.project.Link.RegUser.User.UserDao;

import java.sql.Timestamp;

import com.project.Link.Commons.User.User;
import com.project.Link.Commons.User.Dao.CommonsUserDao;

public interface UserDao extends CommonsUserDao{
	
	// 프로파일 Get은 공통기능처리 => 상속
	
	// 유효성 검사
	public boolean validate(String key, String value);
	// 회원가입
	public int regist(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName, int usrLevel, Timestamp signInDate);
	// 로그인
	public User get(String usrId, String usrPw);
	// 비밀번호변경 등 계정정보 변경
	public boolean update(String usrId, String newPw);
	// 탈퇴
	public boolean delete(String usrId, Timestamp signOutDate);
}
