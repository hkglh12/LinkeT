package com.project.Link.Admin.AdminDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.project.Link.Commons.User.User;
import com.project.Link.Dbinfo.DBinfo;

@Component
public class AdminDaoImple implements AdminDao{
 	private final String driver = DBinfo.getDriver();
 	private final String url = DBinfo.getUrl();
 	private final String dbId = DBinfo.getUserid();
 	private final String dbPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
	@Override
	public User get(String adminId, String adminPw) {
		User admin = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbId, dbPw);
			String sql = "select * from users where u_id = ? and u_pw = ? and u_outdate IS NULL and u_kickeddate IS NULL and u_level=99";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,adminId);
			pstmt.setString(2,adminPw);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				admin = new User();
				admin.setUsrId(rs.getString("u_id"));
				admin.setUsrPw(rs.getString("u_pw"));
				admin.setUsrLevel(rs.getInt("u_level"));
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
		return admin;
	}
 	
 	
}
