package com.project.Link.Comment.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.Link.Comment.Comment;
import com.project.Link.Community.Dao.CommunityDaoImple;
import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.Posting.Posting;

@Component
public class CommentDaoImple implements CommentDao{
	private static final Logger logger = LoggerFactory.getLogger(CommentDaoImple.class);
	public final String targetBoard = "communitycomments";
	public final String prefix = "cc_";
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;

	
	 @Override 
	 public int getTotalCount(int communitySerial) {
		 logger.info("// getTotal Count called"); 
		 int result = 0;
		 try {
			 Class.forName(dbDriver);
			 conn = DriverManager.getConnection(dbUrl, dbUserId,dbUserPw); 
			 String sql = "select count(*) as count from " + targetBoard +" where c_serial = ? AND cc_deletedate IS NULL";
			 pstmt = conn.prepareStatement(sql); 
			 pstmt.setInt(1, communitySerial);
			 rs = pstmt.executeQuery();
			 result = (rs.next()) == true ? rs.getInt("count") : -1;
		 }catch(ClassNotFoundException e) {
			 e.printStackTrace();
		 }catch(SQLException e){
			 e.printStackTrace();
		 }finally{
			 try{ 
				 if (pstmt!=null) pstmt.close();
				 if (conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
				}
			 }
		 return result; }
	 
	@Override
	public int createComment(String usrId, int communitySerial, String contents, Timestamp createDate, boolean isSecret) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//create community
			// 멤버변수 prefix 를 사용하려 했으나 쿼리문이 지나치게 난잡해지므로 사용포기
			String sql = "insert into "+targetBoard+" (c_serial, u_id, cc_contents, cc_createdate, issecret) values(?,?,?,?,?)";
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
	public ArrayList<Comment> getListComment(int communitySerial, int page, int pagePerBlock) {
		logger.info("::getListPosting called");
 		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+targetBoard+" where c_serial = ? AND cc_deletedate IS NULL Order by cc_serial desc LIMIT "+(page*pagePerBlock) + ", "+pagePerBlock;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, communitySerial);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Comment comment = new Comment();
				comment.setSerial(rs.getInt("cc_serial"));
				comment.setUsrId(rs.getString("u_id"));
				/*comment.setUsrBannedId(rs.getString("u_id"));*/
				comment.setCommunitySerial(rs.getInt("c_serial"));
				comment.setContents(rs.getNString("cc_contents"));
				comment.setCreateDate(rs.getTimestamp("cc_createdate"));
				comment.setSecret(rs.getBoolean("issecret"));
				list.add(comment);
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
	public int updateComment(int serial, String contents, Timestamp modifyDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+targetBoard+" set cc_contents = ?, cc_modifyDate = ? where cc_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,contents);
			pstmt.setTimestamp(2, modifyDate);
			pstmt.setInt(3,serial);
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
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+targetBoard+" set cc_deletedate = ? where cc_serial = ? ";
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
