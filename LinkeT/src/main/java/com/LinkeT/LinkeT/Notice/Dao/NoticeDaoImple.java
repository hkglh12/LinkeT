package com.LinkeT.LinkeT.Notice.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.LinkeT.LinkeT.Notice.Notice;

@Component
public class NoticeDaoImple implements NoticeDao{
	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/LinkeT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;

	@Override
	public int countTeamNotice(String teamCode) {
		int result = 0;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select count(*) as count from noticeboard where t_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,teamCode);
			//TODO : 암호화를하던 뭔가 추가를하던
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
			System.out.println(result);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
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
	public int createNotice(int teamNoticeSerial, String teamCode, String usrId, String noticeTitle,
			String noticeContent, String noticeFileCode1, String noticeFileCode2) {
			int result = 0;
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, userId, userPw);
				String sql = "insert into noticeboard (tn_serial, t_code, u_id, n_title, n_content, n_createdate, n_filecode1, n_filecode2 values(?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,teamNoticeSerial);
				pstmt.setString(2,teamCode);
				pstmt.setString(3,usrId);
				pstmt.setString(4,noticeTitle);
				pstmt.setString(5,noticeContent);
				pstmt.setString(6,noticeFileCode1);
				//TODO : 암호화를하던 뭔가 추가를하던
				pstmt.setString(3,usrId);
				result = pstmt.executeUpdate();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				return "";
			}catch(SQLException e) {
				e.printStackTrace();
				return "";
			}finally {	
				try {
					if (pstmt!=null) pstmt.close();
					if (conn!=null) conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(result >=1 && teamCode !="") {
				return teamCode;			
			}else {
				return "";
			}
		return 0;
	}

	@Override
	public ArrayList<Notice> listNotice(String teamCode, int noticePageBlock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice readNotice(String teamCode, int teamNoticeSerial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateNotice(String teamCode, int teamNoticeSerial, HashMap<String, String> target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNotice(String teamCode, int teamNoticeSerial) {
		// TODO Auto-generated method stub
		return false;
	}

}
