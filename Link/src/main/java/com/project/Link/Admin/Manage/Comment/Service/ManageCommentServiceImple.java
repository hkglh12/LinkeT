package com.project.Link.Admin.Manage.Comment.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.Link.Admin.Manage.Comment.Dao.ManageCommentDao;
import com.project.Link.Commons.Comment.Service.CommonsCommentServiceImple;

@Service
@Qualifier("ManageCommentService")
public class ManageCommentServiceImple extends CommonsCommentServiceImple implements ManageCommentService{
	
	@Autowired
	@Qualifier("ManageCommentDao")
	private ManageCommentDao mcDao;
	
	@Override
	public boolean banComment(int targetSerial, String usrId) {
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		return mcDao.banComment(targetSerial, usrId, deleteDate);
	}


}
