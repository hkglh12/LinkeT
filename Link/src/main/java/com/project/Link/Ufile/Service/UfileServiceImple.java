package com.project.Link.Ufile.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.Link.HomeController;
import com.project.Link.Ufile.UFile;
import com.project.Link.Ufile.Dao.UfileDao;

@Service
public class UfileServiceImple implements UfileService {
	private static final Logger logger = LoggerFactory.getLogger(UfileServiceImple.class);
	//TODO file absolute file path need to be re-configured
	private static final String filePath = "C:\\Users\\Administrator\\Desktop\\spr\\Link";
	
	@Autowired
	private UfileDao fDao;
	
	public UfileServiceImple() {}
	
	public UfileDao getfDao() {
		return fDao;
	}
	public void setfDao(UfileDao fDao) {
		this.fDao = fDao;
	}
	@Override
	public int uFileUpload(String targetBoard, String modifiedFileName, String usrId, long fileSize, Timestamp createDate, String originalFileName, int serial){
		logger.info("FileUpload called : " + targetBoard);
		if(originalFileName.length() >= 30) {originalFileName = originalFileName.substring(0, 30);}
		int result = fDao.uploadFile(targetBoard, modifiedFileName, usrId, fileSize, createDate, originalFileName, serial);
		return result;
	}

	@Override
	public ArrayList<UFile> uFileGet(String targetBoard, int targetSerial) {
		logger.info("FileGet called : " + targetBoard);
		ArrayList<UFile> result = new ArrayList<UFile>();
		result = fDao.getFileList(targetBoard, targetSerial);
		return result;
	}

	@Override
	public void uFileDetach(String targetBoard, String targetCode, String usrId, Timestamp disconnDate) throws Exception{
		logger.info("fileDetachedCalled");
		fDao.detachFile(targetBoard, targetCode, usrId, disconnDate);
	}

}
