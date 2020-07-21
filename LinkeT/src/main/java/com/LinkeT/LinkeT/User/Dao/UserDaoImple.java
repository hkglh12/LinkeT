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

	@Override
	public int usrJoinTeam(String usrId, String teamCode) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			
			String usrgetsql = "select * from users where usrid = ?";
			pstmt = conn.prepareStatement(usrgetsql);
			pstmt.setString(1, usrId);
			rs=pstmt.executeQuery();
			
			//DAO니까 service로 올려야할거같기도 하다. 일단 쳐보자
			if(!rs.next()) {
				
				//이거 아이디 검색 안될때임. 잘생각해서 지우기도해야함 리펙토링때 지울것
				return -1;
			}
			ArrayList<String> teams = new ArrayList<String>();
			teams.add(rs.getString("team1"));
			teams.add(rs.getString("team2"));
			teams.add(rs.getString("team3"));
			int ck = 1;
			for(String i : teams) {
				if(i != null) {
					ck++;
				}
			}
			if(ck >=4) {
				// 금지를 나타내고싶었음.
				return 403;
			}
			// teamcode봐야하는데 db가 teamname보고 있어서 에러남
			// TODO 20200723
			// User의 가입, organizationchart 동기화, 쿠키. 이 세개만 하자 내일은
			String sql = "update users set team"+ck+" = "+teamCode+" where usrid = ?";
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
