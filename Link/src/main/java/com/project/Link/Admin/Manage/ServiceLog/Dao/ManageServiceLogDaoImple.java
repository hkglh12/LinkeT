package com.project.Link.Admin.Manage.ServiceLog.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.project.Link.Admin.Manage.ServiceLog.ServiceLog;
import com.project.Link.Dbinfo.DBinfo;

@Component
public class ManageServiceLogDaoImple implements ManageServiceLogDao{
	String dbDriver = DBinfo.getDriver();
	String dbUrl = DBinfo.getUrl();
	String dbUserId = DBinfo.getUserid();
	String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private Statement stmt = null;
 	private ResultSet rs = null;
	@Override
	public int getTotalCount(String baseSql) {
		int result = 0;
		try {
			//DB접속
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			//회원가입 시도
			stmt = conn.createStatement();
			rs = stmt.executeQuery(baseSql);
			while(rs.next()) {
				result = rs.getInt("count");
			}
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
	public ArrayList<ServiceLog> getLogs(String baseSql) {
		ArrayList<ServiceLog> list = new ArrayList<ServiceLog>();
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl,dbUserId,dbUserPw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(baseSql);
			while(rs.next()) {
				ServiceLog log = new ServiceLog();
				log.setIpAddr(rs.getString("ip_addr"));
				log.setOccurTime(rs.getTimestamp("occurtime"));
				log.setUsrId(rs.getString("u_id"));
				log.setTargetService(rs.getString("targetService"));
				log.setResultStatus(rs.getString("resultstatus"));
				log.setMethod(rs.getString("method"));
				log.setRequiredTime(rs.getLong("required_time"));
				list.add(log);
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
