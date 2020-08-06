package com.project.Link.RegUser.Posting.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.Link.HomeController;
import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDaoImple;

@Component
public class PostingDaoImple implements PostingDao{
	private static final Logger logger = LoggerFactory.getLogger(PostingDaoImple.class);
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;

 	public PostingDaoImple() {
 		logger.info("				DaoLevel : PostingDaoImple Constructor Called");
 		
 	};
 	
 	// 유효한 포스팅의 갯수를 가져옴
	@Override
	public int getTotalCount(String targetBoard, String prefix, String subject) {
		logger.info("				DaoLevel : PostingDaoImple///// getTotalCount /////For : " + targetBoard + "table\n");
		
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from " + targetBoard +" where "+prefix+"deletedate IS NULL and c_subject = ?";
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
	
	// 게시글 조회횟수를 증가
	@Override
	public int countUp(String targetBoard, String prefix, int targetSerial, int count) {
		logger.info("				DaoLevel : PostingDaoImple /////Countup For///// : " + targetBoard + "table\n");
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update "+targetBoard+" set "+prefix+"count = "+count+" where "+prefix+"serial = "+targetSerial;
			pstmt = conn.prepareStatement(sql);
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
	
	//게시글 마지막 PK를 가져옴. (FileDao)와의 연계를 고려함
	@Override
	public int getLastSerial(String targetBoard, String prefix) {
		logger.info("				DaoLevel : PostingDaoImple /////GetLastSerial///// : " + targetBoard + "table\n");
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
	//게시글 생성
	@Override
	public int createPosting(String targetBoard, String prefix, int serial, String usrId, String title, String contents, int fileCount,
			Timestamp createDate, String subject) {
		logger.info("				DaoLevel : PostingDaoImple /////CreatePosting///// : " + targetBoard + "table\n");
 		int result = 0;
		String[] psql= {prefix+"serial", prefix+"title", prefix+"contents", prefix+"createdate"};
 		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			// 멤버변수 prefix 를 사용하려 했으나 쿼리문이 지나치게 난잡해지므로 사용포기
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
	
	/*
	 * //특정 게시글 수정
	 * 
	 * @Override public int updatePosting(String targetBoard, String prefix, String
	 * usrId, int targetSerial, String title, String contents, int fileCount,
	 * Timestamp modifyDate) { logger.
	 * info("				DaoLevel : PostingDaoImple /////UpdatePosting///// : " +
	 * targetBoard + "table\n"); int result = 0; try { //DB접속
	 * Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl, dbUserId,
	 * dbUserPw); //회원가입 시도 String[] psql = {prefix+"title", prefix+"contents",
	 * String sql = "update "
	 * +targetBoard+" set n_title = ?, n_contents = ?, f_count = ?, n_modifyDate = ?, u_modified = ? where n_serial = ? "
	 * ; pstmt = conn.prepareStatement(sql); pstmt.setString(1,title);
	 * pstmt.setString(2,contents); pstmt.setInt(3,fileCount);
	 * pstmt.setTimestamp(4,modifyDate); pstmt.setString(5,usrId);
	 * pstmt.setInt(6,targetSerial); result = pstmt.executeUpdate();
	 * 
	 * }catch(ClassNotFoundException e) { e.printStackTrace(); }catch(SQLException
	 * e) { e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) { e.printStackTrace(); } }
	 * return result; }
	 */
	/*
	 * @Override public int deletePosting(String usrId, int targetSerial, Timestamp
	 * deleteDate) { // TODO Auto-generated method stub return 0; }
	 * 
	 */
 	
	/*
	 * @Override public int get(String targetBoard, String prefix, int serial) { int
	 * result = -1; try { Class.forName(driver); conn =
	 * DriverManager.getConnection(url, userId, userPw);
	 * 
	 * String sql = "select " + prefix + "_count" + " from " +targetBoard+
	 * " where "+ (prefix + "_serial")+" = ?"; pstmt = conn.prepareStatement(sql);
	 * pstmt.setInt(1, serial); rs = pstmt.executeQuery(); if(rs.next()) { result =
	 * rs.getInt(prefix+"_count"); System.out.println(result); }else { result = 0; }
	 * 
	 * }catch(ClassNotFoundException e) { e.printStackTrace(); }catch(SQLException
	 * e) { e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) { e.printStackTrace(); } }
	 * return result; }
	 * 
	 * @Override public int update(String targetBoard,String prefix, int count, int
	 * serial) { int result = -1; try { Class.forName(driver); conn =
	 * DriverManager.getConnection(url, userId, userPw);
	 * 
	 * String sql = "update "+targetBoard+" set "+ prefix + "_count=? where "+
	 * (prefix + "_serial")+" = ?"; pstmt = conn.prepareStatement(sql);
	 * pstmt.setInt(1, count); pstmt.setInt(2, serial); result =
	 * pstmt.executeUpdate();
	 * 
	 * }catch(ClassNotFoundException e) { e.printStackTrace(); }catch(SQLException
	 * e) { e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) { e.printStackTrace(); } }
	 * return result;
	 * 
	 * }
	 */
}

