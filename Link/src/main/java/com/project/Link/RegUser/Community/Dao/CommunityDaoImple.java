package com.project.Link.RegUser.Community.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.Community.Community;
import com.project.Link.Commons.Community.Dao.CommonsCommunityDaoImple;
import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDaoImple;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.RegUser.Posting.Dao.PostingDaoImple;

@Component
@Qualifier("UserCommunityDao")
public class CommunityDaoImple extends CommonsCommunityDaoImple implements CommunityDao{
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
 	public CommunityDaoImple() {}

	@Override
	public int getLastSerial(String targetBoard, String prefix) {
		// 게시판의 마지막 Serial number를 리턴
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
	
	@Override
	public int createPosting(String targetBoard, String prefix, int serial, String usrId, String title, String contents, int fileCount,	Timestamp createDate, String subject) {
		//게시판에 새로운 글을 등록
		int result = 0;
		String[] psql= {prefix+"serial", prefix+"title", prefix+"contents", prefix+"createdate"};
 		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "insert into "+targetBoard+" ("+psql[0]+", u_id, "+psql[1]+", "+psql[2]+", f_count, "+psql[3]+", c_subject) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,serial);
			pstmt.setString(2, usrId);
			pstmt.setString(3,title);
			pstmt.setString(4,contents);
			pstmt.setInt(5,fileCount);
			pstmt.setTimestamp(6,createDate);
			pstmt.setNString(7, subject);
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
	public int updateCommunity(int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate) {
		// 특정 게시글 수정
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update community set c_title = ?, c_contents = ?, f_count = ?, c_modifyDate = ? where c_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,contents);
			pstmt.setInt(3,fileCount);
			pstmt.setTimestamp(4,modifyDate);
			pstmt.setInt(5,targetSerial);
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
	public int deleteCommunity(int targetSerial, Timestamp deleteDate) {
		// 특정 게시글 삭제
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update community set c_deletedate = ? where c_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,deleteDate);
			pstmt.setInt(2,targetSerial);
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

