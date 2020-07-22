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
			String sql = "insert into team (t_code, t_name, t_owner) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,teamName);
			pstmt.setString(2,usrId);
			//TODO : 암호화를하던 뭔가 추가를하던
			pstmt.setString(3, teamName+usrId);
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
	public Team getTeam(String teamCode) {
		// TODO Auto-generated method stub
		Team team = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "select * from team where t_code= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,teamCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				team = new Team();
				team.setTeamName(rs.getString("t_name"));
				team.setTeamOwner(rs.getString("t_owner"));
				team.setTeamCode(rs.getString("t_code"));
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
		return team;
	}

}
