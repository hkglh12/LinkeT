package com.project.Link.RegUser.Comment.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.Comment.Dao.CommonsCommentDaoImple;
import com.project.Link.Dbinfo.DBinfo;

@Component
@Qualifier("UserCommentDao")
public class CommentDaoImple extends CommonsCommentDaoImple implements CommentDao{
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;

	@Override
	public int createComment(String usrId, int communitySerial, String contents, Timestamp createDate, boolean isSecret) {
		// 댓글 작성
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "insert into communitycomments (c_serial, u_id, cc_contents, cc_createdate, issecret) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,communitySerial);
			pstmt.setString(2, usrId);
			pstmt.setString(3,contents);
			pstmt.setTimestamp(4,createDate);
			pstmt.setBoolean(5,isSecret);
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

	@Override
	public int updateComment(int serial, String contents, boolean isSecret, Timestamp modifyDate) {
		// 댓글 수정
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update communitycomments set cc_contents = ?, cc_modifyDate = ?, issecret = ? where cc_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,contents);
			pstmt.setTimestamp(2, modifyDate);
			pstmt.setBoolean(3,isSecret);
			pstmt.setInt(4,serial);
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

	@Override
	public int deleteComment(int serial, String usrId, Timestamp deleteDate) {
		// 댓글 삭제
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update communitycomments set cc_deletedate = ? where cc_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,deleteDate);
			pstmt.setInt(2, serial);
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
