package com.project.Link.Admin.Manage.User.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Admin.Manage.Comment.Service.ManageCommentService;
import com.project.Link.Admin.Manage.Community.Service.ManageCommunityService;
import com.project.Link.Admin.Manage.User.Dao.ManageUserDao;
import com.project.Link.Commons.User.Service.CommonsUserServiceImple;
import com.project.Link.RegUser.Comment.Service.CommentService;
import com.project.Link.RegUser.Community.Service.CommunityService;
import com.project.Link.RegUser.User.User;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("ManageUserService")
public class ManageUserServiceImple extends CommonsUserServiceImple implements ManageUserService {
	private int amount = 10;
	@Autowired
	@Qualifier("ManageUserDao")
	private ManageUserDao muDao;
	@Autowired
	private UfileService ufService;
	@Autowired
	@Qualifier("ManageCommunityService")
	private ManageCommunityService cService;
	@Autowired
	@Qualifier("ManageCommentService")
	private ManageCommentService ccService;

	@Override
	public ArrayList<User> getUsers(String mainCategory, int page, String searchTarget, String subCategory) {
		// Dao는 데이터베이스에 접근/리턴만 하게하기 위해 여러 타입에 대한 DAO 선언,
		// 각각 1.전체유저 2.탈퇴/강퇴유저 3. 정상유저 리턴메서드
		ArrayList<User> userList = new ArrayList<User>();

		if(!(subCategory.isEmpty())) subCategory = "u_"+subCategory;
		System.out.println(subCategory);
		if(mainCategory.equals("all")) {
			if(subCategory.isEmpty())	userList = muDao.getAll(amount, page);
			else userList = muDao.getTarget(amount, page, subCategory, searchTarget);
		}else if(mainCategory.equals("banout")) {
			if(subCategory.isEmpty()) userList = muDao.getBannedOutUsers(amount, page);
			else userList = muDao.getTargetBannedOutUsers(amount, page, subCategory, searchTarget);
		}else if(mainCategory.equals("normal")) {
			if(subCategory.isEmpty()) userList = muDao.getNormalUsers(amount, page);
			else userList = muDao.getTargetNormalUsers(amount, page, subCategory, searchTarget);
		}else if(mainCategory.equals("admin")) {
			if(subCategory.isEmpty()) userList = muDao.getAdmin(amount, page);
			else userList = muDao.getTargetAdmin(amount, page, subCategory, searchTarget);
		}
		for(User u : userList) {
			u.setFileCount(ufService.getUserFileCount(u.getUsrId()));
			u.setCommentCount(ccService.getUserCommentsCount(u.getUsrId()));
			u.setCommunityCount(cService.userCountCommunities(u.getUsrId()));
		}
		return userList;
	}

	@Override
	public int getCountUser(String mainCategory, String subCategory ,String searchTarget) {
		int result = 0;

		if(!(subCategory.isEmpty())) subCategory = "u_"+subCategory;
		if(mainCategory.equals("all")) {
			if(subCategory.isEmpty()) result = muDao.getAllCount();
			else result = muDao.getTargetCount(subCategory, searchTarget);
		}else if(mainCategory.equals("banout")) {
			if(subCategory.isEmpty()) result = muDao.getBannedOutCount();
			else result = muDao.getTargetBannedOutCount(subCategory, searchTarget);
		}else if(mainCategory.equals("normal")) {
			if(subCategory.isEmpty()) result = muDao.getNormalCount();
			else result = muDao.getTargetNormalCount(subCategory, searchTarget);
		}else if(mainCategory.equals("admin")) {
			if(subCategory.isEmpty()) result = muDao.getAdminCount();
			else result = muDao.getTargetAdminCount(subCategory, searchTarget);
		}
		return result;
	}

	@Override
	public boolean banUsers(String[] targetList, String adminId) {
		// 타겟 유저아이디와 admin을 구분하기위해 인자에서 이름을 바꿔서 취급
		// targetUserId는 "강퇴대상", amdinId는 "강퇴를 진행하는 관리자아이디"
		// DAO가 데이터베이스에 접근만 할뿐, 제어권을 갖지않게하기위해서 반복문처리
		boolean result = false;
		for(String targetUserId : targetList) {
			Timestamp userKickedDate = Timestamp.valueOf(LocalDateTime.now());
			result = muDao.banUser(adminId, targetUserId, userKickedDate) >= 1? true : false;
			if(result == false) {
				return false;
			}
		}
		return result;
	}


}
