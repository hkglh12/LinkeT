package com.project.Link.Noticement.Dao;

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

import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.Posting.Posting;
import com.project.Link.User.User;

@Component
public class NoticementDaoImple implements NoticementDao{
	private static final Logger logger = LoggerFactory.getLogger(NoticementDaoImple.class);
	public final String targetBoard = "noticement";
	public final String prefix = "n_";
	
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
 	@Override
 	public int countUp(String targetBoard, int targetserial, int count) {
 		logger.info("::CountUp Called");
 		int result = 0;
 		
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update "+targetBoard+" set n_count = "+count+" where n_serial = "+targetserial;
			pstmt = conn.prepareStatement(sql);
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
 	public int getTotalCount() {
 	//공지사항의 총 게시글 개수를 가져오는 메서드
 		logger.info("::getTotalCount called");
 		int result = 0;
 		
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from " + targetBoard;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = (rs.next()) == true ? rs.getInt("count") : -1;	
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
 	public int getLastSerial() {
 	// 마지막 게시글의 PK를 가져옴. >> FileDao 연계고려
 		logger.info("::getLastSerial called");
 		int result = 0;
 		
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String target = prefix+"serial";
			String sql = "select "+target+" from "+targetBoard+" order by " + target +" desc limit 1";
			logger.warn(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = (rs.next()) == true ? rs.getInt(target) : -1;	
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
	/*
	 * @Override public int total() { int result = 0; try { //DB접속
	 * Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl, dbUserId,
	 * dbUserPw); //회원가입 시도 String sql = "select count(*) as count from noticement";
	 * pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()) { result = rs.getInt("count"); }
	 * }catch(ClassNotFoundException e) { e.printStackTrace(); }catch(SQLException
	 * e) { e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) { e.printStackTrace(); } }
	 * return result; }
	 */
 	@Override
 	public int createPosting(int serial,String usrId, String title, String contents, int fileCount, Timestamp createDate) {
 		logger.info("::createPosting called");
 		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			// 멤버변수 prefix 를 사용하려 했으나 쿼리문이 지나치게 난잡해지므로 사용포기
			String sql = "insert into "+targetBoard+" (n_serial, u_id, n_title, n_contents, f_count, n_createdate) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,serial);
			pstmt.setString(2, usrId);
			pstmt.setString(3,title);
			pstmt.setString(4,contents);
			pstmt.setInt(5,fileCount);
			pstmt.setTimestamp(6,createDate);
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

	/*
	 * @Override public int create(String usrId, String ntcTitle, String
	 * ntcContents, int fileCount, Timestamp createDate) { int result = 0; try {
	 * //DB접속 Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl,
	 * dbUserId, dbUserPw); //회원가입 시도 String sql =
	 * "insert into noticement (u_id, n_title, n_contents, f_count, n_createdate) values(?,?,?,?,?)"
	 * ; pstmt = conn.prepareStatement(sql); pstmt.setString(1,usrId);
	 * pstmt.setString(2,ntcTitle); pstmt.setString(3,ntcContents);
	 * pstmt.setInt(4,fileCount); pstmt.setTimestamp(5,createDate); result =
	 * pstmt.executeUpdate(); }catch(ClassNotFoundException e) {
	 * e.printStackTrace(); }catch(SQLException e) { e.printStackTrace(); }finally {
	 * try { if (pstmt!=null) pstmt.close(); if (conn!=null) conn.close();
	 * }catch(SQLException e) { e.printStackTrace(); } } return result; }
	 */
 	
 	@Override
 	public ArrayList<Posting> getListPosting(int page, int pagePerBlock){
 		logger.info("::getListPosting called");
 		ArrayList<Posting> list = new ArrayList<Posting>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+targetBoard+" where n_deletedate IS NULL Order by n_serial desc Limit " + (page*pagePerBlock) +","+pagePerBlock;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Posting posting = new Posting();
				posting.setSerial(rs.getInt("n_serial"));
				posting.setUsrId(rs.getString("u_id"));
				posting.setTitle(rs.getString("n_title"));
				posting.setContents(rs.getString("n_contents"));
				posting.setFileCount(rs.getInt("f_count"));
				posting.setCreateDate(rs.getTimestamp("n_createdate"));
				posting.setModifyDate(rs.getTimestamp("n_modifydate"));
				posting.setNoticeCount(rs.getInt("n_count"));
				list.add(posting);
			}
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
		return list;
 	}

	/*
	 * @Override public ArrayList<Posting> list(int page, int pagePerBlock) {
	 * ArrayList<Posting> list = new ArrayList<Posting>(); try {
	 * Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl, dbUserId,
	 * dbUserPw); String sql =
	 * "select * from noticement where n_deletedate IS NULL Order by n_serial desc Limit "
	 * + (page*pagePerBlock) +","+pagePerBlock; pstmt = conn.prepareStatement(sql);
	 * 
	 * rs = pstmt.executeQuery(); while(rs.next()) { Posting posting = new
	 * Posting(); posting.setSerial(rs.getInt("n_serial"));
	 * posting.setUsrId(rs.getString("u_id"));
	 * posting.setTitle(rs.getString("n_title"));
	 * posting.setContents(rs.getString("n_contents"));
	 * posting.setFileCount(rs.getInt("f_count"));
	 * posting.setCreateDate(rs.getTimestamp("n_createdate"));
	 * posting.setModifyDate(rs.getTimestamp("n_modifydate"));
	 * posting.setNoticeCount(rs.getInt("n_count")); list.add(posting); }
	 * }catch(ClassNotFoundException e) { e.printStackTrace(); }catch(SQLException
	 * e) { e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) { e.printStackTrace(); } }
	 * return list; }
	 */
 	
	@Override
	public Posting getPosting(int targetSerial) {
		logger.info("::getPosting called");
		Posting posting = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+targetBoard+" where n_serial = ? and n_deletedate IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,targetSerial);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				posting = new Posting();
				posting.setSerial(rs.getInt("n_serial"));
				posting.setUsrId(rs.getString("u_id"));
				posting.setTitle(rs.getString("n_title"));
				posting.setContents((rs.getString("n_contents")));
				posting.setFileCount(rs.getInt("f_count"));
				posting.setCreateDate(rs.getTimestamp("n_createdate"));
				posting.setNoticeCount(rs.getInt("n_count"));
			}
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
		return posting;
	}

	@Override
	public int updatePosting(String usrId,int targetSerial,  String ntcTitle, String ntcContents, int fileCount, Timestamp modifyDate) {
		logger.info("::updatePosting called");
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+targetBoard+" set n_title = ?, n_contents = ?, f_count = ?, n_modifyDate = ? where n_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,ntcTitle);
			pstmt.setString(2,ntcContents);
			pstmt.setInt(3,fileCount);
			pstmt.setTimestamp(4,modifyDate);
			pstmt.setInt(5,targetSerial);
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
	public int deletePosting(String usrId, int targetSerial, Timestamp deleteDate) {
		logger.info("::deletePosting called");
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			String sql = "update "+targetBoard+" set n_deletedate = ?, u_modified = ? where n_serial = ? ";
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

	
}
