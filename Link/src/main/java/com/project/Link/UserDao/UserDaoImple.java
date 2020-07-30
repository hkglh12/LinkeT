package com.project.Link.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.project.Link.User.User;

@Component
public class UserDaoImple implements UserDao {
 	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/Link?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public boolean validate(String key, String value) {
		boolean result = true;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);

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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
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
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from users where u_id = ? and u_pw = ? and u_outdate IS NULL and u_kickeddate IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			System.out.println(usrId + " : " + usrPw);
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
	public User get(String usrId) {
		User user = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from users where u_id = ? and u_outdate is NULL";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,usrId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrPw(rs.getString("u_pw"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrLevel(rs.getInt("u_level"));
				user.setUsrInDate(rs.getTimestamp("u_indate"));
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
	public int update(String usrId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(String usrId) {
		// TODO Auto-generated method stub
		return 0;
	}
}
