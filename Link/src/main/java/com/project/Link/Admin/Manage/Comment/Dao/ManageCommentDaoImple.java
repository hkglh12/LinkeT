package com.project.Link.Admin.Manage.Comment.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.Comment.Dao.CommonsCommentDaoImple;
import com.project.Link.Dbinfo.DBinfo;

@Component
@Qualifier("ManageCommentDao")
public class ManageCommentDaoImple extends CommonsCommentDaoImple implements ManageCommentDao {
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public boolean banComment(int targetSerial, String usrId, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update communitycomments set cc_deletedate = ?, isbanned = ?, u_banned_id = ? where cc_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,deleteDate);
			pstmt.setBoolean(2,  true);
			pstmt.setString(3, usrId);
			pstmt.setInt(4,targetSerial);
			result = pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return result >=1 ? true:false;
	}
}
