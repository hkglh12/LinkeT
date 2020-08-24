package com.project.Link.RegUser.Posting.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;

@Component
public class PostingDaoImple implements PostingDao{
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	public PostingDaoImple() {};
	
	// 게시글 조회횟수를 증가
	@Override
	public int countUp(String targetBoard, String prefix, int targetSerial, int count) {
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update "+targetBoard+" set "+prefix+"count = "+count+" where "+prefix+"serial = "+targetSerial;
			pstmt = conn.prepareStatement(sql);
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
}

