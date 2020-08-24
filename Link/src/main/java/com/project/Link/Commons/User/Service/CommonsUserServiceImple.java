package com.project.Link.Commons.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Commons.Comment.Service.CommonsCommentService;
import com.project.Link.Commons.Community.Service.CommonsCommunityService;
import com.project.Link.Commons.User.User;
import com.project.Link.Commons.User.Dao.CommonsUserDao;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("CommonsUserService")
public class CommonsUserServiceImple implements CommonsUserService{
	
	@Autowired
	@Qualifier("CommonsUserDao")
	private CommonsUserDao uDao;
	@Autowired
	private UfileService ufService;
	@Autowired
	@Qualifier("CommonsCommentService")
	private CommonsCommentService ccService;
	@Autowired
	@Qualifier("CommonsCommunityService")
	private CommonsCommunityService cService;
	
	
	public CommonsUserDao getuDao() {return uDao;}
	public void setuDao(CommonsUserDao uDao) {this.uDao = uDao;}
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}
	public CommonsCommentService getCcService() {return ccService;}
	public void setCcService(CommonsCommentService ccService) {	this.ccService = ccService;	}
	public CommonsCommunityService getcService() {return cService;}
	public void setcService(CommonsCommunityService cService) {this.cService = cService;}

	@Override
	/* 회원정보가져오기로 쓰임 >> 관리자, 사용자는 본인정보 자세히보기..*/
	public User getUserDetail(String usrId) {
		User user = uDao.get(usrId);
		return user;
	}
}
