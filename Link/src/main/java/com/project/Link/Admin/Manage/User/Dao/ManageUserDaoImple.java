package com.project.Link.Admin.Manage.User.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.User.User;
import com.project.Link.Dbinfo.DBinfo;

@Component
@Qualifier("ManageUserDao")
public class ManageUserDaoImple implements ManageUserDao{
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
	
	@Override
	//모든 "사용자"의 개수 출력
	public int getAllCount() {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	// 모든 "사용자" 리스트 출력 (단, 20개씩 끊어서)
	@Override
	public ArrayList<User> getAll(int amount, int page) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 1 order by u_level desc, u_indate desc limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}
	// 조건에 부합하는 사용자의 개수 출력
	@Override
	public int getTargetCount(String subCategory, String searchTarget) {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 1 and "+subCategory+" like ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	// 조건에 부합하는 "모든 사용자" 출력
	@Override
	public ArrayList<User> getTarget(int amount, int page, String subCategory, String searchTarget) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 1 and "+subCategory+" like ? order by u_indate limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}
	
	// 강퇴 혹은 탈퇴 한 사람의 개수 출력
	@Override
	public int getBannedOutCount() {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 1 and (u_outdate is not null or u_kickeddate is not null)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	// 강퇴 혹은 탈퇴 한 사람 출력
	@Override
	public ArrayList<User> getBannedOutUsers(int amount, int page) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 1 and (u_outdate is not null or u_kickeddate is not null) order by u_indate desc limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}
	
	//정상사용자 개수 출력
	@Override
	public int getNormalCount() {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 1 and (u_outdate is null and u_kickeddate is null)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	
	// 정상사용자 출력
	@Override
	public ArrayList<User> getNormalUsers(int amount, int page) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 1 and (u_outdate is null and u_kickeddate is null) order by u_level desc, u_indate desc limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}
	


	@Override
	public int banUser(String adminId, String targetUserId, Timestamp userKickedDate) {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update users set u_kickeddate = ?, u_banned_id =? where u_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setTimestamp(1, userKickedDate);
			pstmt.setString(2, adminId);
			pstmt.setNString(3, targetUserId);
			result = pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	@Override
	public int getAdminCount() {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 99";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}
	@Override
	public ArrayList<User> getAdmin(int amount, int page){
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 99 order by u_indate limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}


	@Override
	public ArrayList<User> getTargetBannedOutUsers(int amount, int page, String subCategory, String searchTarget) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 1 and (u_kickeddate is not null or u_outdate is not null) and "+subCategory+" like ? order by u_indate limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}

	@Override
	public ArrayList<User> getTargetNormalUsers(int amount, int page, String subCategory, String searchTarget) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 1 and (u_kickeddate is null and u_outdate is null) and "+subCategory+" like ? order by u_indate limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}

	@Override
	public ArrayList<User> getTargetAdmin(int amount, int page, String subCategory, String searchTarget) {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_level = 99 and "+subCategory+" like ? order by u_indate limit " +(amount*page)+", "+amount;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getNString("u_banned_id"));
				user.setUsrLevel(rs.getInt("u_level"));
				list.add(user);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}


	@Override
	public int getTargetBannedOutCount(String subCategory, String searchTarget) {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 1 and (u_kickeddate is not null or u_outdate is not null) and "+subCategory+" like ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}

	@Override
	public int getTargetNormalCount(String subCategory, String searchTarget) {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 1 and (u_kickeddate is null and u_outdate is null) and "+subCategory+" like ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}

	@Override
	public int getTargetAdminCount(String subCategory, String searchTarget) {
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from users where u_level = 99 and "+subCategory+" like ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchTarget+"%");
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result;
	}


}
