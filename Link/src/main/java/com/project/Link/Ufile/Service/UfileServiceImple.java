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
	
	//TODO file absolute file path need to be re-configured
	private static final String filePath = "C:\\Users\\Administrator\\Desktop\\spr\\Link";
	
	@Autowired
	private UfileDao fDao;
	
	public UfileServiceImple() {}
	
	public UfileDao getfDao() {return fDao;}
	public void setfDao(UfileDao fDao) {this.fDao = fDao;}
	
	@Override
	public int uFileUpload(String targetBoard, String modifiedFileName, String usrId, long fileSize, Timestamp createDate, String originalFileName, int serial){
		if(originalFileName.length() >= 30) {originalFileName = originalFileName.substring(0, 30);}
		int result = fDao.uploadFile(targetBoard, modifiedFileName, usrId, fileSize, createDate, originalFileName, serial);
		return result;
	}
	@Override
	public ArrayList<UFile> uFileGet(String targetBoard, int targetSerial) {
		ArrayList<UFile> result = new ArrayList<UFile>();
		result = fDao.getFileList(targetBoard, targetSerial);
		return result;
	}
	@Override
	public boolean uFileValidate(String targetBoard, String fileCode) {
		boolean result = fDao.uFileCodeValidate(targetBoard, fileCode);
		return result;
	}
	@Override
	public void uFileDetach(String targetBoard, String targetCode, String usrId, Timestamp disconnDate) throws Exception{
		fDao.detachFile(targetBoard, targetCode, usrId, disconnDate);
	}

	@Override
	public int getUserFileCount(String usrId) {
		return fDao.getUserFileCount(usrId);
	}

}
