package com.project.Link.Posting.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.Link.HomeController;
/*
@Component
public class PostingDaoImple implements PostingDao{
	
 	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/Link?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public int get(String targetBoard, String prefix, int serial) {
		int result = -1;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);

			String sql = "select " + prefix + "_count" + " from " +targetBoard+ " where "+ (prefix + "_serial")+" = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  serial);
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				result = rs.getInt(prefix+"_count");
				System.out.println(result);
			}else {
				result = 0;
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
	public int update(String targetBoard,String prefix, int count, int serial) {
		int result = -1;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);

			String sql = "update "+targetBoard+" set "+ prefix + "_count=? where "+ (prefix + "_serial")+" = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  count);
			pstmt.setInt(2,  serial);
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
*/
