package com.project.Link.Admin.Manage.Community.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Community.Dao.CommunityDaoImple;

@Component
@Qualifier("AdminCommunityDao")
public class ManageCommunityDaoImple extends CommunityDaoImple implements ManageCommunityDao{
	private final String ctargetBoard = "community";
	private static final String cctargetBoard = "communitycomments";
	public final String cprefix = "c_";
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public boolean banCommunity(int targetSerial, String usrId, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+ctargetBoard+" set c_deletedate = ?, isbanned = ?, u_banned_id = ? where c_serial = ? ";
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
	@Override
	public boolean banComment(int targetSerial, String usrId, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+cctargetBoard+" set cc_deletedate = ?, isbanned = ?, u_banned_id = ? where cc_serial = ? ";
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
	@Override
	public boolean bulkBanCommunity(int targetSerial, String usrId, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+ctargetBoard+" set c_deletedate = ?, isbanned = ?, u_banned_id = ? where c_serial = ? ";
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
