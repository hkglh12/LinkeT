package com.project.Link.Commons.AOPLogger.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;

@Component
public class ServiceLevelLoggerDaoImple implements ServiceLevelLoggerDao{
	// 따로 매핑이 필요한 서비스가 아니므로 바로 DAO를 호출합니다.
	protected String dbDriver = DBinfo.getDriver();
	protected String dbUrl = DBinfo.getUrl();
	protected String dbUserId = DBinfo.getUserid();
	protected String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	
	// Service레벨에 접근할때 먼저 DB에 발자취를 남깁니다.
	// 이후 결과처리는 afterreturn에서 처리합니다.
	public void loggerBefore(String ip, Timestamp sessionStamp, String usrId, String targetUri, String method) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "insert into servicelevellog (ip_addr, occurtime, u_id, targetservice, method) value(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  ip);
			pstmt.setTimestamp(2, sessionStamp);
			pstmt.setString(3,usrId);
			pstmt.setString(4, targetUri);
			pstmt.setString(5, method);
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();
		}
	}
}

	@Override
	public void afterSuccess(Timestamp sessionStampStr, long requiredTime) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update servicelevellog set resultstatus = ?, required_time = ? where occurtime = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"success");
			pstmt.setLong(2, requiredTime);
			pstmt.setTimestamp(3, sessionStampStr);
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();
		}
	}
	}

	@Override
	public void afterFailed(Timestamp sessionStampStr, long requiredTime) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "update servicelevellog set resultstatus = ?, required_time = ? where occurtime = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"failed");
			pstmt.setLong(2, requiredTime);
			pstmt.setTimestamp(3, sessionStampStr);
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {e.printStackTrace();
		}catch(SQLException e) {e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(SQLException e) {e.printStackTrace();
		}
	}
	}
}
