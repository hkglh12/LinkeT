package com.project.Link.Community.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.project.Link.Posting.Posting;

@Component
public class CommunityDaoImple implements CommunityDao{
	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/Link?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public int create(String usrId, String cmtyTitle, String cmtyContents, int fileCount, Timestamp createDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "insert into community (u_id, c_title, c_contents, f_count, c_createdate) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,cmtyTitle);
			pstmt.setString(3,cmtyContents);
			pstmt.setInt(4,fileCount);
			pstmt.setTimestamp(5,createDate);
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
	public ArrayList<Posting> list(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Posting get(int targetSerial) {
		Posting posting = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from community where c_serial = ? and c_deletedate IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,targetSerial);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				posting = new Posting();
				posting.setSerial(rs.getInt("c_serial"));
				posting.setUsrId(rs.getString("u_id"));
				posting.setTitle(rs.getString("c_title"));
				posting.setContents((rs.getString("c_contents")));
				posting.setFileCount(rs.getInt("f_count"));
				posting.setCreateDate(rs.getTimestamp("c_createdate"));
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
		return posting;
	}

	@Override
	public int update(int targetSerial, String cmtyTitle, String cmtyContents, int fileCount, Timestamp modifyDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "update community set c_title = ?, c_contents = ?, f_count = ?, c_modifyDate = ? where c_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,cmtyTitle);
			pstmt.setString(2,cmtyContents);
			pstmt.setInt(3,fileCount);
			pstmt.setTimestamp(4,modifyDate);
			pstmt.setInt(5,targetSerial);
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
	public int delete(int targetSerial, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "update community set c_deletedate = ? where c_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,deleteDate);
			pstmt.setInt(2,targetSerial);
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
	}

