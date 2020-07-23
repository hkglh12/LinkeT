package com.LinkeT.LinkeT.User.Dao;

import org.springframework.stereotype.Component;

import com.LinkeT.LinkeT.User.User;
import java.sql.*;
import java.util.ArrayList;

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
	public boolean validate(String key, String value) {
 		boolean result = true;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//검색시도
			String sql = "select * from users where "+key+" = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,value);
			System.out.println(pstmt);
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();	
			System.out.println(rs);
			System.out.println(rs.toString());
			if(rs.next()) {
				//어차피 한개라도 검색에 성공했다면, 이미 있는것. 따라서 while이아니라 if
				result = true;
				System.out.println("search true");
			}else {
				result = false;
				System.out.println("search false");
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
	public int usrInsert(String usrId, String usrPw, String usrPhone, String usrEmail, String usrName) {
		//회원가입
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "insert into users (u_id, u_pw, u_name, u_phone, u_email) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			pstmt.setString(3,usrName);
			pstmt.setString(4,usrPhone);
			pstmt.setString(5,usrEmail);
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
		// 실패했다면0, 성공했다면 1이 return될것
		return result;
	}

	@Override
	public User logInUser(String usrId, String usrPw) {
		//로그인
		User user = null;
		//유저 정보를 "객체 전체" 대상으로 검색해서 대상을 리턴, service에서 일부만 걸러서 다시올려보냄 << getUser랑 합쳐질 여지가 있음
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from users where u_id = ? and u_Pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,usrPw);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrPw(rs.getString("u_Pw"));
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
			String sql = "select * from users where u_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,usrId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setUsrId(rs.getString("u_id"));
				user.setUsrPw(rs.getString("u_Pw"));
				user.setUsrName(rs.getString("u_name"));
				user.setUsrPhone(rs.getString("u_phone"));
				user.setUsrEmail(rs.getString("u_email"));
				user.setUsrTeamcount(rs.getInt("u_teamcount"));
				
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
	public int usrJoinTeam(String usrId, String teamCode) {		//존재하는 workspace에 가입
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			
			String usrgetsql = "select * from users where u_id = ?";	// 사용자 계정을 먼저 찾는다
			pstmt = conn.prepareStatement(usrgetsql);
			pstmt.setString(1, usrId);
			rs=pstmt.executeQuery();
			
			//DAO니까 service로 올려야할거같기도 하다. 일단 쳐보자
			if(!rs.next()) {
				
				//이거 아이디 검색 안될때임. 잘생각해서 지우기도해야함 리펙토링때 지울것
				return -1;
			}
			// teamcode봐야하는데 db가 teamname보고 있어서 에러남
			// TODO 20200723
			// User의 가입, organizationchart 동기화, 쿠키. 이 세개만 하자 내일은/test
			String sql = "update users set u_teamcount = (select u_teamcount from users where u_id = ?) + 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			
			int result = pstmt.executeUpdate();
			System.out.println("USER DAO UPDATE " + result);
			return result;
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
		return 0;
	}

	

}
