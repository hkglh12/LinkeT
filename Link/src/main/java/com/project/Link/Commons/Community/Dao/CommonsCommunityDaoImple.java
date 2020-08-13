package com.project.Link.Commons.Community.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.Community.Community;
import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Posting.Dao.PostingDaoImple;

@Component
@Qualifier("CommonsCommunityDao")
public class CommonsCommunityDaoImple extends PostingDaoImple implements CommonsCommunityDao{
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	

	@Override
	public int getTotalCount(String subject) {
		// subject별 삭제처리되지 않은 게시글의 개수를 가져옴
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from community where c_deletedate IS NULL and c_subject = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, subject);
			rs = pstmt.executeQuery();
			result = (rs.next()) == true ? rs.getInt("count") : -1;	
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
	public int userCountCommunities(String usrId) {
		//특정 유저가 작성한 게시글의 개수를 리턴
		int result=0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from community where u_id=? and c_deletedate is null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1,  usrId);
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : -1;
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
	public int getSearchCount(String targetBoard, String prefix, String searchCategory, String searchTarget, String subject) {
		// 특정 검색 조건에 부합하는 게시글의 개수를 리턴
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from " + targetBoard +" where "+prefix+"deletedate IS NULL and "+searchCategory+" like ? and c_subject = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTarget+"%");
			pstmt.setNString(2, subject);
			rs = pstmt.executeQuery();
			result = (rs.next()) == true ? rs.getInt("count") : -1;	
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
	public Community getCommunity(int targetSerial) {
		Community community = new Community();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from community where c_serial = ? and c_deletedate IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,targetSerial);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				community.setSerial(rs.getInt("c_serial"));
				community.setUsrId(rs.getString("u_id"));
				community.setTitle(rs.getString("c_title"));
				community.setContents((rs.getString("c_contents")));
				community.setFileCount(rs.getInt("f_count"));
				community.setCreateDate(rs.getTimestamp("c_createdate"));
				community.setReadCount(rs.getInt("c_count"));
				community.setSubject(rs.getString("c_subject"));
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {	
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();
			}
		}
		return community;
	}
	
	@Override
	public ArrayList<Community> getListCommunity(int page, int pagePerBlock, String communitySubject) {
		// 페이징 처리되어 요청된 특정 개수의 게시글을 리턴
 		ArrayList<Community> list = new ArrayList<Community>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from community where c_deletedate IS NULL and c_subject = ? Order by c_serial desc Limit " + (page*pagePerBlock) +", "+pagePerBlock;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, communitySubject);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Community community = new Community();
				community.setSerial(rs.getInt("c_serial"));
				community.setUsrId(rs.getString("u_id"));
				community.setTitle(rs.getString("c_title"));
				community.setContents(rs.getString("c_contents"));
				community.setFileCount(rs.getInt("f_count"));
				community.setCreateDate(rs.getTimestamp("c_createdate"));
				community.setModifyDate(rs.getTimestamp("c_modifydate"));
				community.setReadCount(rs.getInt("c_count"));
				community.setSubject(rs.getNString("c_subject"));
				list.add(community);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {	
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}
	
	@Override
	public ArrayList<Community> searchListCommunity(int targetPage, int pagePerBlock, String searchCategory, String searchTarget, String communitySubject) {
		// 특정 조건 하에 부합하는 게시글의 리스트를 리턴
 		ArrayList<Community> list = new ArrayList<Community>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from community where c_deletedate IS NULL and "+searchCategory+" like ? and c_subject = ? Order by c_serial desc Limit " + (targetPage*pagePerBlock) +", "+pagePerBlock;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTarget+"%");
			pstmt.setString(2, communitySubject);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Community community = new Community();
				community.setSerial(rs.getInt("c_serial"));
				community.setUsrId(rs.getString("u_id"));
				community.setTitle(rs.getString("c_title"));
				community.setContents(rs.getString("c_contents"));
				community.setFileCount(rs.getInt("f_count"));
				community.setCreateDate(rs.getTimestamp("c_createdate"));
				community.setModifyDate(rs.getTimestamp("c_modifydate"));
				community.setReadCount(rs.getInt("c_count"));
				community.setSubject(rs.getNString("c_subject"));
				list.add(community);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {	
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}

	@Override
	public Community getLastUserCommunity(String usrId) {
		Community community = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from community where c_deletedate IS NULL and u_id = ? order by c_createdate desc Limit 0,1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usrId);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				community = new Community();
				community.setSerial(rs.getInt("c_serial"));
				community.setUsrId(rs.getString("u_id"));
				community.setTitle(rs.getString("c_title"));
				community.setContents(rs.getString("c_contents"));
				community.setFileCount(rs.getInt("f_count"));
				community.setCreateDate(rs.getTimestamp("c_createdate"));
				community.setModifyDate(rs.getTimestamp("c_modifydate"));
				community.setReadCount(rs.getInt("c_count"));
				community.setSubject(rs.getNString("c_subject"));		
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {	
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return community;
	}

	@Override
	public ArrayList<Community> directSerachUserCommunity(int targetPage, int pagePerBlock, String searchCategory,
			String searchTarget) {
		ArrayList<Community> list = new ArrayList<Community>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from community where c_deletedate IS NULL and "+searchCategory+" like ? Order by c_serial desc Limit " + (targetPage*pagePerBlock) +", "+pagePerBlock;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTarget+"%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Community community = new Community();
				community.setSerial(rs.getInt("c_serial"));
				community.setUsrId(rs.getString("u_id"));
				community.setTitle(rs.getString("c_title"));
				community.setContents(rs.getString("c_contents"));
				community.setFileCount(rs.getInt("f_count"));
				community.setCreateDate(rs.getTimestamp("c_createdate"));
				community.setModifyDate(rs.getTimestamp("c_modifydate"));
				community.setReadCount(rs.getInt("c_count"));
				community.setSubject(rs.getNString("c_subject"));
				list.add(community);
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {	
			try {
				if (pstmt!=null) pstmt.close();
				if (conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();}
		}
		return list;
	}
}
