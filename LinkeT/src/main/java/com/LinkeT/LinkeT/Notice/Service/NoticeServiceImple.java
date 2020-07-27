package com.LinkeT.LinkeT.Notice.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.LinkeT.LinkeT.Notice.Notice;
import com.LinkeT.LinkeT.Notice.Dao.NoticeDao;

@Service
public class NoticeServiceImple implements NoticeService{
	private NoticeDao nDao;
	
	public NoticeServiceImple() {}
	@Override
	public boolean createNotice(int teamNoticeSerial, String teamCode, String usrId, String noticeTitle,
			String noticeContent, String noticeFileCode1, String noticeFileCode2) {
		int counter = nDao.countTeamNotice(teamCode);
		int result = 0;
		if(counter==-1) {
			return false;
		}else {
			result = nDao.createNotice(teamNoticeSerial, teamCode, usrId, noticeTitle, noticeContent, noticeFileCode1, noticeFileCode2);
		}
		boolean finalresult = result >=1 ? true : false;
		return finalresult;
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
