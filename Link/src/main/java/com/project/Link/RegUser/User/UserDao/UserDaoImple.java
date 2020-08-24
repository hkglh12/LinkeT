package com.project.Link.RegUser.User.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.User.User;
import com.project.Link.Commons.User.Dao.CommonsUserDaoImple;
import com.project.Link.Dbinfo.DBinfo;

@Component
@Qualifier("UserDao")
public class UserDaoImple extends CommonsUserDaoImple implements UserDao {
	protected String dbDriver = DBinfo.getDriver();
	protected String dbUrl = DBinfo.getUrl();
	protected String dbUserId = DBinfo.getUserid();
	protected String dbUserPw = DBinfo.getUserpw();
	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public boolean validate(String key, String value) {
		boolean result = true;
		
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);

			String sql = "select * from users where "+key+" = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,value);
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				result = true;
			}else {
				result = false;
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int regist(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName, int usrLevel, Timestamp signInDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "insert into users (u_id, u_pw, u_name, u_phone, u_email, u_level, u_indate) values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			pstmt.setString(3,usrName);
			pstmt.setString(4,usrPhone);
			pstmt.setString(5,usrEmail);
			pstmt.setInt(6, usrLevel);
			pstmt.setTimestamp(7, signInDate);
			result = pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public User get(String usrId, String usrPw) {
		User user = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_id = ? and u_pw = ? and u_outdate IS NULL and u_kickeddate IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrPw(rs.getString("u_pw"));
				user.setUsrLevel(rs.getInt("u_level"));
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {	
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean update(String usrId, String newPw) {
		int result = 0;
		boolean rResult = false;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update users set u_pw = ? where u_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newPw);
			pstmt.setNString(2, usrId);
			result = pstmt.executeUpdate();
			rResult= result >=1 ? true : false;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rResult;
	}
	@Override
	public boolean delete(String usrId, Timestamp signOutDate) {
		int result = 0;
		boolean rResult = false;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);

			String sql = "update users set u_outdate = ? where u_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,signOutDate);
			pstmt.setString(2, usrId);
			result = pstmt.executeUpdate();
			rResult= result >=1 ? true : false;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rResult;
	}
}
