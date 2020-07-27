package com.LinkeT.LinkeT.Notice.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.LinkeT.LinkeT.Notice.Notice;

public interface NoticeDao {
	/* 팀 총 게시글 개수 세기 */
	public int countTeamNotice(String teamCode);
	/* create */
	public int createNotice(int teamNoticeSerial, String teamCode, String usrId, String noticeTitle, String noticeContent, String noticeFileCode1, String noticeFileCode2);
	/* Listing */
	public ArrayList<Notice> listNotice(String teamCode, int noticePageBlock);
	/* read */
	public Notice readNotice(String teamCode, int teamNoticeSerial);
	/* Update */
	public boolean updateNotice(String teamCode, int teamNoticeSerial, HashMap<String, String> target);
	/* Delete */
	public boolean deleteNotice(String teamCode, int teamNoticeSerial);
}
