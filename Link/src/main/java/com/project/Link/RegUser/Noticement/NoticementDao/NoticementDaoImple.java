package com.project.Link.RegUser.Noticement.NoticementDao;

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

import com.project.Link.Commons.User.User;
import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.RegUser.Posting.Dao.PostingDaoImple;

@Component
@Qualifier("noticeDao")
public class NoticementDaoImple extends PostingDaoImple implements NoticementDao{
	// 해당 다오는 noticement로만 접근합니다.
	private String dbDriver = DBinfo.getDriver();
	private String dbUrl = DBinfo.getUrl();
	private String dbUserId = DBinfo.getUserid();
	private String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
 	public NoticementDaoImple() {}

 	@Override
 	// 
 	public ArrayList<Noticement> getListNoticement(int page, int pagePerBlock){
 		
 		ArrayList<Noticement> list = new ArrayList<Noticement>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from noticement where n_deletedate IS NULL Order by n_serial desc Limit " + (page*pagePerBlock) +","+pagePerBlock;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Noticement noticement = new Noticement();
				noticement.setSerial(rs.getInt("n_serial"));
				noticement.setUsrId(rs.getString("u_id"));
				noticement.setTitle(rs.getString("n_title"));
				noticement.setContents(rs.getString("n_contents"));
				noticement.setFileCount(rs.getInt("f_count"));
				noticement.setCreateDate(rs.getTimestamp("n_createdate"));
				noticement.setModifyDate(rs.getTimestamp("n_modifydate"));
				noticement.setReadCount(rs.getInt("n_count"));
				noticement.setModifiedUsr(rs.getString("u_modified_id"));
				list.add(noticement);
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
	public Noticement getNoticement(int targetSerial) {
		// 특정 공지글을 불러옵니다.
		Noticement noticement = new Noticement();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from noticement where n_serial = ? and n_deletedate IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,targetSerial);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				noticement.setSerial(rs.getInt("n_serial"));
				noticement.setUsrId(rs.getString("u_id"));
				noticement.setTitle(rs.getString("n_title"));
				noticement.setContents((rs.getString("n_contents")));
				noticement.setFileCount(rs.getInt("f_count"));
				noticement.setCreateDate(rs.getTimestamp("n_createdate"));
				noticement.setReadCount(rs.getInt("n_count"));
				noticement.setModifiedUsr(rs.getNString("u_modified_id"));
				noticement.setModifyDate(rs.getTimestamp("n_modifydate"));
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
		return noticement;
	}

	@Override
	public int getNoticementCount() {
		// 삭제처리되지 않은 공지글의 개수를 가져옵니다
		int result = 0;
 		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from noticement where n_deletedate IS NULL";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			result = rs.next() == true ? rs.getInt("count") : 0;	
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
