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

import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Community.Community;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDaoImple;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.RegUser.Posting.Dao.PostingDaoImple;

@Component
@Qualifier("UserCommunityDao")
public class CommunityDaoImple extends PostingDaoImple implements CommunityDao{
	private static final Logger logger = LoggerFactory.getLogger(CommunityDaoImple.class);
	public final String ctargetBoard = "community";
	public final String cprefix = "c_";
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
 	public CommunityDaoImple() {
 		logger.info("				DaoLvel : CommunityDamImple Constructor Called");
 	}
	@Override
	public ArrayList<Community> getListCommunity(int page, int pagePerBlock, String communitySubject) {
		logger.info("				DaoLvel : CommunityDamImple //////GetListCommunity////// Called");
 		ArrayList<Community> list = new ArrayList<Community>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+ctargetBoard+" where c_deletedate IS NULL and c_subject = ?Order by c_serial desc Limit " + (page*pagePerBlock) +", "+pagePerBlock;

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, communitySubject);
			rs = pstmt.executeQuery();
			
			System.out.println(sql);
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
	public Community getCommunity(int targetSerial) {
		logger.info("				DaoLvel : CommunityDamImple //////GetTargetCommunity////// Called");
		Community community = new Community();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+ctargetBoard+" where c_serial = ? and c_deletedate IS NULL";
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
	public int updateCommunity(int targetSerial,  String title, String contents, int fileCount, Timestamp modifyDate) {
		logger.info("				DaoLvel : CommunityDamImple //////TargetUpdateCommunity////// Called");
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+ctargetBoard+" set c_title = ?, c_contents = ?, f_count = ?, c_modifyDate = ? where c_serial = ? ";
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
		logger.info("				DaoLvel : CommunityDamImple //////TargetDeleteCommunity////// Called");
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+ctargetBoard+" set c_deletedate = ? where c_serial = ? ";
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


	@Override
	public ArrayList<Community> searchListCommunity(int targetPage, int pagePerBlock, String searchCategory,
			String searchTarget, String communitySubject) {
		logger.info("				DaoLvel : CommunityDamImple //////GetListCommunity////// Called");
 		ArrayList<Community> list = new ArrayList<Community>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+ctargetBoard+" where c_deletedate IS NULL and "+searchCategory+" like ? and c_subject = ? Order by c_serial desc Limit " + (targetPage*pagePerBlock) +", "+pagePerBlock;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTarget+"%");
			pstmt.setString(2, communitySubject);
			rs = pstmt.executeQuery();
			System.out.println(sql);
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
	public int getSearchCount(String targetBoard, String prefix, String searchCategory, String searchTarget, String subject) {
		logger.info("				DaoLevel : PostingDaoImple///// getTotalCount /////For : " + targetBoard + "table\n");
		
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
	public int userCountCommunities(String usrId) {
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



	}

