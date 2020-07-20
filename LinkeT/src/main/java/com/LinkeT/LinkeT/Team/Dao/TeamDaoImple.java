package com.LinkeT.LinkeT.Team.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.LinkeT.LinkeT.Team.Team;
import com.LinkeT.LinkeT.User.User;

@Component
public class TeamDaoImple implements TeamDao{
	private final String driver = "com.mysql.cj.jdbc.Driver";
 	private final String url = "jdbc:mysql://localhost:3306/LinkeT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
 	private final String userId = "root";
 	private final String userPw = "root";
 	
 	private Connection conn = null;
 	private PreparedStatement pstmt = null;
 	private ResultSet rs = null;
	
	@Override
	public int createTeam(String usrId, String teamName) {
		int result = 0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "insert into team (teanname, teamowner, teamcode) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,usrId);
			pstmt.setString(2,teamName);
			//TODO : 암호화를하던 뭔가 추가를하던
			pstmt.setString(3, usrId+teamName);
			result = pstmt.executeUpdate();
			System.out.println(result);
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
	public ArrayList<usrin> getTeam(String usrId) {
		// TODO Auto-generated method stub
		ArrayList<usrin> teamlist = new ArrayList();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select teamname, usrgrade from organizationchart where usrid = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,usrId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				usrin inset = new usrin();
				inset.setTeamname(rs.getString("teamname"));
				inset.setUsrgrade(rs.getNString("usrgrade"));
				teamlist.add(inset);
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
		return teamlist;
	}

}
