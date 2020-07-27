package com.project.Link.Noticement.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.project.Link.Posting.Posting;
import com.project.Link.User.User;

@Component
public class NoticementDaoImple implements NoticementDao{

	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/Link?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
 	
 	@Override
	public int total() {
 		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "select count(*) as count from noticement";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}

 
	@Override
	public int create(String usrId, String ntcTitle, String ntcContents, int fileCount, Timestamp createDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "insert into noticement (u_id, n_title, n_contents, f_count, n_createdate) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,ntcTitle);
			pstmt.setString(3,ntcContents);
			pstmt.setInt(4,fileCount);
			pstmt.setTimestamp(5,createDate);
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
	public ArrayList<Posting> list(int page, int pagePerBlock) {
		ArrayList<Posting> list = new ArrayList<Posting>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from noticement where n_deletedate IS NULL Order by n_serial desc Limit " + (page*pagePerBlock) +","+pagePerBlock;
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

	@Override
	//TODO 각각 댓글 생성을 완료하고나면 이쪽에서 댓글도 같이 줘야함
	public Posting get(int targetSerial) {
		Posting posting = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from noticement where n_serial = ? and n_deletedate IS NULL";
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
	public int update(int targetSerial, String ntcTitle, String ntcContents, int fileCount, Timestamp modifyDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "update noticement set n_title = ?, n_contents = ?, f_count = ?, n_modifyDate = ? where n_serial = ? ";
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
	public int delete(int targetSerial, Timestamp deleteDate) {
		int result = 0;
		try {
			//DB접속
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			//회원가입 시도
			String sql = "update noticement set n_deletedate = ? where n_serial = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1,deleteDate);
			pstmt.setInt(2,targetSerial);
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
