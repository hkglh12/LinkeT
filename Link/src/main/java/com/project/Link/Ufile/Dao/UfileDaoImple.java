package com.project.Link.Ufile.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.Ufile.UFile;

@Component
public class UfileDaoImple implements UfileDao{
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
 	
	public UfileDaoImple() {}

	@Override
	public int uploadFile(String targetBoard, String modifiedFileName, String usrId, long fileSize,
			Timestamp createDate, String originalFileName, int serial) {

 		int result = 0;
 		try {
 			String prefix = targetBoard.substring(0,1)+"_";
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			System.out.println(targetBoard);
			String sql = "insert into "+targetBoard+" (f_code, u_id, f_size, f_createdate, f_originname, "+prefix+"serial) values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modifiedFileName);
			pstmt.setNString(2, usrId);
			pstmt.setLong(3, fileSize);
			pstmt.setTimestamp(4, createDate);
			pstmt.setString(5, originalFileName);
			pstmt.setInt(6, serial);
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
	public ArrayList<UFile> getFileList(String targetBoard,int relativeSerial) {
		ArrayList<UFile> uFileList = new ArrayList<UFile>();

		String prefix = targetBoard.substring(0, 1) +"_";

		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			
			String sql = "select * from "+targetBoard+" where "+prefix+"serial = ? and isdisconn IS FALSE";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,relativeSerial);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UFile ufile = new UFile();
				ufile.setuFileCode(rs.getString("f_code"));
				ufile.setUsrId(rs.getNString("u_id"));
				ufile.setFileSize(rs.getLong("f_size"));
				ufile.setuFilePostDate(rs.getTimestamp("f_createdate"));
				ufile.setuFileOriginName(rs.getString("f_originname"));
				ufile.setSerial(rs.getInt(prefix+"serial"));
				uFileList.add(ufile);
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
		return uFileList;
	}

	@Override
	public boolean uFileCodeValidate(String targetBoard, String fileCode) {
		boolean result = false;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select * from "+targetBoard+" where f_code = ? and isdisconn = false";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void detachFile(String targetBoard, String targetCode, String usrId, Timestamp disconnDate) {
		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update "+targetBoard+" set isdisconn = true, f_disconndate = ?, f_disconn_id = ? where f_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, disconnDate);
			pstmt.setString(2, usrId);
			pstmt.setString(3, targetCode);
			pstmt.executeUpdate();
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
	}

	@Override
	public int getUserFileCount(String usrId) {
		int result = 0;
		try {
 			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "select count(*) as count from communityfile where u_id = ? and isdisconn IS FALSE";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usrId);
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
}
