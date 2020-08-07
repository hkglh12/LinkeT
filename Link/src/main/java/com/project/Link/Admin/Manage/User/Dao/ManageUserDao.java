package com.project.Link.Admin.Manage.User.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Link.RegUser.User.User;

public interface ManageUserDao {
	// 전체유저
	public ArrayList<User> getAll(int amount, int page);
	// 강퇴당한유저
	public ArrayList<User> getBannedOutUsers(int amount, int page);
	// 정상사용가능유저
	public ArrayList<User> getNormalUsers(int amount, int page);
	// 관리자출력
	public ArrayList<User> getAdmin(int amount, int page);
	
	public int getAllCount();
	public int getBannedOutCount();
	public int getNormalCount();
	public int getAdminCount();
	
	// 주어진 조건이 있는경우
	// 조건 하 전체사용자
	public ArrayList<User> getTarget(int amount, int page, String subCategory, String searchTarget);
	// 조건 하 강퇴/탈퇴 사용자
	public ArrayList<User> getTargetBannedOutUsers(int amount, int page, String subCategory, String searchTarget);
	// 조건 하 정상사용자
	public ArrayList<User> getTargetNormalUsers(int amount, int page, String subCategory, String searchTarget);
	// 조건 하 관리자
	public ArrayList<User> getTargetAdmin(int amount, int page, String subCategory, String searchTarget);
	
	public int getTargetCount(String subCategory, String searchTarget);
	public int getTargetBannedOutCount(String subCategory, String searchTarget);
	public int getTargetNormalCount(String subCategory, String searchTarget);
	public int getTargetAdminCount(String subCategory, String searchTarget);
	
	public int banUser(String adminId, String targetUserId, Timestamp userKickedDate);
}
