package com.LinkeT.LinkeT.User.Dao;

import org.springframework.stereotype.Component;

import com.LinkeT.LinkeT.User.User;
import java.sql.*;

@Component
public class UserDaoImple implements UserDao{

 	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/mydb";
 	private final String userId = "root";
 	private final String userPw = "1234";
 	
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
			String sql = "insert into users (userid, userpw, username, userphone, useremail) values (?,?,?,?,?)";
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
		}finally {	// 자원해제
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
	public User usrLogin(String usrId, String usrPw) {
		// TODO Auto-generated method stub
		return null;
	}

}
