package com.project.Link.Admin.Manage.Noticement.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDaoImple;

@Component
@Qualifier("manageNoticeDao")
public class ManageNoticementDaoImple extends NoticementDaoImple implements ManageNoticementDao{
	//public final String ntargetBoard = "noticement";
	//public final String nprefix = "n_";
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	@Override
	public int updateNoticement(String usrId,int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update noticement set n_title = ?, n_contents = ?, f_count = ?, n_modifyDate = ?, u_modified_id = ? where n_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,contents);
			pstmt.setInt(3,fileCount);
			pstmt.setTimestamp(4,modifyDate);
			pstmt.setString(5,usrId);
			pstmt.setInt(6,targetSerial);
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
	public int deleteNoticement(String usrId, int targetSerial, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update noticement set n_deletedate = ?, u_modified_id = ? where n_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,deleteDate);
			pstmt.setString(2, usrId);
			pstmt.setInt(3,targetSerial);
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

	//게시글 생성
	@Override
	public int createNoticement(String targetBoard, String prefix, int serial, String usrId, String title, String contents, int fileCount, Timestamp createDate) {
 		int result = 0;
		String[] psql= {prefix+"serial", prefix+"title", prefix+"contents", prefix+"createdate"};
 		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "insert into "+targetBoard+" ("+psql[0]+", u_id, "+psql[1]+", "+psql[2]+", f_count, "+psql[3]+") values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,serial);
			pstmt.setString(2, usrId);
			pstmt.setString(3,title);
			pstmt.setString(4,contents);
			pstmt.setInt(5,fileCount);
			pstmt.setTimestamp(6,createDate);
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
	public int getLastSerial(String targetBoard, String prefix) {
		// 마지막 Serial 넘버를 가져옴
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String target = prefix+"serial";
			String sql = "select "+target+" from "+targetBoard+" order by " + target +" desc limit 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = (rs.next()) == true ? rs.getInt(target) : 0;	
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
