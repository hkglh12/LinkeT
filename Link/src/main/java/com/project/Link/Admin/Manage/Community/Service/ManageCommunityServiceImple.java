package com.project.Link.Admin.Manage.Community.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Admin.Manage.Community.Dao.ManageCommunityDao;
import com.project.Link.Commons.Community.Service.CommonsCommunityServiceImple;
import com.project.Link.RegUser.Community.Dao.CommunityDao;

@Service
@Qualifier("ManageCommunityService")
public class ManageCommunityServiceImple extends CommonsCommunityServiceImple implements ManageCommunityService{
	/* totalCountCommunities(특정 게시글 개수 count),
	   DirectCountCommunities(특정 유저의 게시글 개수 count),
	   GetCommunity (게시글 Read), ListCommunities(게시글 Listing),
	   DirectListCommunities(특정 유저 Listing)
	   상기 기능은 유저-관리자 공통기능이므로 Commons에서 상속
	   */
	// 사용하는 변수 (테이블명, 파일경로, pagePerBlock, prefix는 Protected로 Commons로부터 상속받아서 사용)
	@Autowired
	@Qualifier("ManageCommunityDao")
	private ManageCommunityDao mcDao;
	
	public ManageCommunityDao getMcDao() {return mcDao;}
	public void setMcDao(ManageCommunityDao mcDao) {this.mcDao = mcDao;}
	
	//게시글 bulkBan
	@Override
	public boolean bulkCommunityBan(String[] list, String usrId) {
		boolean result = false;
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		for(int i=0; i<list.length; i++) {
			result = mcDao.bulkBanCommunity(Integer.valueOf(list[i]), usrId, deleteDate);
			if(result == false) {
				break;
			}
		}
		return result;
	}
	
	// 특정게시글 Ban
	@Override
	public boolean banCommunity(int targetSerial, String usrId) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		return mcDao.banCommunity(targetSerial,usrId, deleteDate);
	}
}
