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
import org.springframework.stereotype.Component;

import com.project.Link.Dbinfo.DBinfo;
import com.project.Link.Posting.Posting;
import com.project.Link.Posting.Dao.PostingDaoImple;
import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.User.User;

//@Component
public class NoticementDaoImplebkup /* extends PostingDaoImple implements NoticementDao */ {
	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(NoticementDaoImplebkup.class); public final String
	 * ntargetBoard = "noticement"; public final String nprefix = "n_";
	 * 
	 * String dbDriver = DBinfo.getDriver(); String dbUrl = DBinfo.getUrl(); String
	 * dbUserId = DBinfo.getUserid(); String dbUserPw = DBinfo.getUserpw();
	 * 
	 * private Connection conn = null; private PreparedStatement pstmt = null;
	 * private ResultSet rs = null;
	 * 
	 * public NoticementDaoImplebkup() {
	 * logger.info("				DaoLvel : NoticementDaoImple Constructor Called"
	 * ); }
	 * 
	 * 
	 * @Override public int getTotalCount() { //공지사항의 총 게시글 개수를 가져오는 메서드
	 * logger.info("::getTotalCount called"); int result = 0;
	 * 
	 * try { Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl,
	 * dbUserId, dbUserPw); String sql = "select count(*) as count from " +
	 * targetBoard +" where n_deletedate IS NULL"; pstmt =
	 * conn.prepareStatement(sql); rs = pstmt.executeQuery(); result = (rs.next())
	 * == true ? rs.getInt("count") : -1; }catch(ClassNotFoundException e)
	 * {e.printStackTrace(); }catch(SQLException e) {e.printStackTrace(); }finally {
	 * try { if (pstmt!=null) pstmt.close(); if (conn!=null) conn.close();
	 * }catch(SQLException e) {e.printStackTrace();} } return result; }
	 * 
	 * 
	 * @Override public int countUp(int targetSerial, int count) {
	 * logger.info("::CountUp Called"); int result = 0; try {
	 * Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl, dbUserId,
	 * dbUserPw); String sql =
	 * "update "+targetBoard+" set n_count = "+count+" where n_serial = "
	 * +targetSerial; pstmt = conn.prepareStatement(sql); result =
	 * pstmt.executeUpdate(); }catch(ClassNotFoundException e) {e.printStackTrace();
	 * }catch(SQLException e) {e.printStackTrace(); }finally { try { if
	 * (pstmt!=null) pstmt.close(); if (conn!=null) conn.close();
	 * }catch(SQLException e) {e.printStackTrace();} } return result; }
	 * 
	 * 
	 * @Override public int getLastSerial() { // 마지막 게시글의 PK를 가져옴. >> FileDao 연계고려
	 * logger.info("::getLastSerial called"); int result = 0;
	 * 
	 * try { Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl,
	 * dbUserId, dbUserPw); String target = prefix+"serial"; String sql =
	 * "select "+target+" from "+targetBoard+" order by " + target +" desc limit 1";
	 * pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery(); result =
	 * (rs.next()) == true ? rs.getInt(target) : -1; }catch(ClassNotFoundException
	 * e) { e.printStackTrace(); }catch(SQLException e) { e.printStackTrace();
	 * }finally { try { if (pstmt!=null) pstmt.close(); if (conn!=null)
	 * conn.close(); }catch(SQLException e) { e.printStackTrace(); } } return
	 * result; }
	 * 
	 * 
	 * @Override public int createPosting(int serial,String usrId, String title,
	 * String contents, int fileCount, Timestamp createDate) {
	 * logger.info("::createPosting called"); int result = 0; try { //DB접속
	 * Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl, dbUserId,
	 * dbUserPw); // 멤버변수 prefix 를 사용하려 했으나 쿼리문이 지나치게 난잡해지므로 사용포기 String sql =
	 * "insert into "
	 * +targetBoard+" (n_serial, u_id, n_title, n_contents, f_count, n_createdate) values(?,?,?,?,?,?)"
	 * ; pstmt = conn.prepareStatement(sql); pstmt.setInt(1,serial);
	 * pstmt.setString(2, usrId); pstmt.setString(3,title);
	 * pstmt.setString(4,contents); pstmt.setInt(5,fileCount);
	 * pstmt.setTimestamp(6,createDate); result = pstmt.executeUpdate();
	 * }catch(ClassNotFoundException e) {e.printStackTrace(); }catch(SQLException e)
	 * {e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) {e.printStackTrace();} }
	 * return result; }
	 * 
	 * @Override public ArrayList<Noticement> getListNoticement(int page, int
	 * pagePerBlock){ logger.
	 * info("				DaoLvel : NoticementDaoImple /////GetListNoticement////// Called"
	 * ); ArrayList<Noticement> list = new ArrayList<Noticement>(); try {
	 * Class.forName(dbDriver); conn = DriverManager.getConnection(dbUrl, dbUserId,
	 * dbUserPw); String sql = "select * from "
	 * +ntargetBoard+" where n_deletedate IS NULL Order by n_serial desc Limit " +
	 * (page*pagePerBlock) +","+pagePerBlock;
	 * 
	 * pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()) { Noticement noticement = new Noticement();
	 * noticement.setSerial(rs.getInt("n_serial"));
	 * noticement.setUsrId(rs.getString("u_id"));
	 * noticement.setTitle(rs.getString("n_title"));
	 * noticement.setContents(rs.getString("n_contents"));
	 * noticement.setFileCount(rs.getInt("f_count"));
	 * noticement.setCreateDate(rs.getTimestamp("n_createdate"));
	 * noticement.setModifyDate(rs.getTimestamp("n_modifydate"));
	 * noticement.setReadCount(rs.getInt("n_count")); list.add(noticement); }
	 * }catch(ClassNotFoundException e) {e.printStackTrace(); }catch(SQLException e)
	 * {e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) {e.printStackTrace();} }
	 * return list; }
	 * 
	 * @Override public Noticement getNoticement(int targetSerial) { logger.
	 * info("				DaoLvel : NoticementDaoImple ////TargetGetNoticement//// Called"
	 * ); logger.info("::getPosting called"); Noticement noticement = new
	 * Noticement(); try { Class.forName(dbDriver); conn =
	 * DriverManager.getConnection(dbUrl, dbUserId, dbUserPw); String sql =
	 * "select * from "+ntargetBoard+" where n_serial = ? and n_deletedate IS NULL";
	 * pstmt = conn.prepareStatement(sql); pstmt.setInt(1,targetSerial); rs =
	 * pstmt.executeQuery(); while(rs.next()) {
	 * noticement.setSerial(rs.getInt("n_serial"));
	 * noticement.setUsrId(rs.getString("u_id"));
	 * noticement.setTitle(rs.getString("n_title"));
	 * noticement.setContents((rs.getString("n_contents")));
	 * noticement.setFileCount(rs.getInt("f_count"));
	 * noticement.setCreateDate(rs.getTimestamp("n_createdate"));
	 * noticement.setReadCount(rs.getInt("n_count")); }
	 * }catch(ClassNotFoundException e) {e.printStackTrace(); }catch(SQLException e)
	 * {e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) {e.printStackTrace(); } }
	 * return noticement; }
	 * 
	 * @Override public int updateNoticement(String usrId,int targetSerial, String
	 * title, String contents, int fileCount, Timestamp modifyDate) { logger.
	 * info("				DaoLvel : NoticementDaoImple ////UpdateNoticement//// Called"
	 * ); int result = 0; try { //DB접속 Class.forName(dbDriver); conn =
	 * DriverManager.getConnection(dbUrl, dbUserId, dbUserPw); //회원가입 시도 String sql
	 * = "update "
	 * +ntargetBoard+" set n_title = ?, n_contents = ?, f_count = ?, n_modifyDate = ?, u_modified = ? where n_serial = ? "
	 * ; pstmt = conn.prepareStatement(sql); pstmt.setString(1,title);
	 * pstmt.setString(2,contents); pstmt.setInt(3,fileCount);
	 * pstmt.setTimestamp(4,modifyDate); pstmt.setString(5,usrId);
	 * pstmt.setInt(6,targetSerial); result = pstmt.executeUpdate();
	 * 
	 * }catch(ClassNotFoundException e) { e.printStackTrace(); }catch(SQLException
	 * e) { e.printStackTrace(); }finally { try { if (pstmt!=null) pstmt.close(); if
	 * (conn!=null) conn.close(); }catch(SQLException e) { e.printStackTrace(); } }
	 * return result; }
	 * 
	 * @Override public int deleteNoticement(String usrId, int targetSerial,
	 * Timestamp deleteDate) { logger.
	 * info("				DaoLvel : NoticementDaoImple ////TargetDeleteNoticement//// Called"
	 * ); int result = 0; try { //DB접속 Class.forName(dbDriver); conn =
	 * DriverManager.getConnection(dbUrl, dbUserId, dbUserPw); //회원가입 시도 String sql
	 * = "update "
	 * +ntargetBoard+" set n_deletedate = ?, u_modified = ? where n_serial = ? ";
	 * pstmt = conn.prepareStatement(sql); pstmt.setTimestamp(1,deleteDate);
	 * pstmt.setString(2, usrId); pstmt.setInt(3,targetSerial); result =
	 * pstmt.executeUpdate(); }catch(ClassNotFoundException e) {
	 * e.printStackTrace(); }catch(SQLException e) { e.printStackTrace(); }finally {
	 * try { if (pstmt!=null) pstmt.close(); if (conn!=null) conn.close();
	 * }catch(SQLException e) { e.printStackTrace(); } } return result; }
	 * 
	 * 
	 */

}
