package com.project.Link.Commons.Comment.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.Link.Commons.Comment.Comment;
import com.project.Link.Dbinfo.DBinfo;

@Component
@Qualifier("CommonsCommentDao")
public class CommonsCommentDaoImple implements CommonsCommentDao{
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	 @Override 
	 public int getTotalCount(int communitySerial) {
		 // 특정 게시물에 대한 댓글 개수를 리턴
		 int result = 0;
		 try {
			 Class.forName(dbDriver);
			 conn = DriverManager.getConnection(dbUrl, dbUserId,dbUserPw); 
			 String sql = "select count(*) as count from communitycomments where c_serial = ? AND cc_deletedate IS NULL";
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
		 return result;
	 }
	@Override
	public ArrayList<Comment> getListComment(int communitySerial, int page, int pagePerBlock) {
		// 특정 게시글의 최초접속시 댓글 1페이지 정보를 제공
 		ArrayList<Comment> list = new ArrayList<Comment>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from communitycomments where c_serial = ? AND cc_deletedate IS NULL Order by cc_serial desc LIMIT "+(page*pagePerBlock) + ", "+pagePerBlock;
			
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
				comment.setCheckSecret(rs.getBoolean("issecret"));
				System.out.println(comment.getCheckSecret());
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
	public int getUserCommentCount(String usrId) {
		// 특정 유저의 댓글 개수를 리턴
		int result = 0;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from communitycomments where u_id=? and cc_deletedate is null ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			rs = pstmt.executeQuery();
			result = rs.next() != false ? rs.getInt("count") : -1;
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
