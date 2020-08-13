package com.project.Link.Commons.User.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.User.User;
import com.project.Link.Dbinfo.DBinfo;

@Component
@Qualifier("CommonsUserDao")
public class CommonsUserDaoImple implements CommonsUserDao{
	protected String dbDriver = DBinfo.getDriver();
	protected String dbUrl = DBinfo.getUrl();
	protected String dbUserId = DBinfo.getUserid();
	protected String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
 	
	@Override
	public User get(String usrId) {
		User user = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from users where u_id = ?";
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
				user.setUsrOutDate(rs.getTimestamp("u_outdate"));
				user.setUsrKickedDate(rs.getTimestamp("u_kickeddate"));
				user.setUsrBannedId(rs.getString("u_banned_id"));
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
