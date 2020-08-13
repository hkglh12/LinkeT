package com.project.Link.Commons.AOPLogger.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;

@Component
public class DaoLevelLoggerDaoImple implements DaoLevelLoggerDao{
	protected String dbDriver = DBinfo.getDriver();
	protected String dbUrl = DBinfo.getUrl();
	protected String dbUserId = DBinfo.getUserid();
	protected String dbUserPw = DBinfo.getUserpw();
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	
	@Override
	public void loggerAfter(String ip, String sessionStamp, String usrId, String targetUri, String sqlquery) {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);
			String sql = "insert into servicelevellog (ip_addr, occurtime, u_id, targetservice, sqlquery) value(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  ip);
			pstmt.setString(2, sessionStamp);
			pstmt.setString(3,usrId);
			pstmt.setString(4, targetUri);
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
