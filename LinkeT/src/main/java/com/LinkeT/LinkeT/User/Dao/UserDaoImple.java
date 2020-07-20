package com.LinkeT.LinkeT.User.Dao;

import org.springframework.stereotype.Component;

import com.LinkeT.LinkeT.User.User;
import java.sql.*;

@Component
public class UserDaoImple implements UserDao{
	
 	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/LinkeT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public int usrInsert(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName) {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "insert into users (usrid, usrpw, usrname, usrphone, usremail) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			pstmt.setString(3,usrName);
			pstmt.setString(4,usrPhone);
			pstmt.setString(5,usrEmail);
			result = pstmt.executeUpdate();
			System.out.println(result);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {	// �옄�썝�빐�젣
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
	public User loginUser(String usrId, String usrPw) {
		User user = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from users where usrid = ? and usrPw = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setUsrId(rs.getString("usrid"));
				user.setUsrPw(rs.getString("usrPw"));
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
	public User getUser(String usrId) {
		User user = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from users where usrid = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,usrId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setUsrId(rs.getString("usrid"));
				user.setUsrPw(rs.getString("usrPw"));
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

}
