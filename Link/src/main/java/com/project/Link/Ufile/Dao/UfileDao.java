package com.project.Link.Ufile.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.project.Link.Ufile.UFile;

public interface UfileDao {
	
	public int uploadFile(String targetBoard, String modifiedFileName, String usrId, long fileSize, Timestamp createDate, String originalFileName, int serial);
	public ArrayList<UFile> getFileList(String targetBoard, int relativeSerial);
	public UFile getFile(String targetBoard, String fileCode);
	public void detachFile(String targetBoard, String targetCode, String usrId, Timestamp disconnDate);
}
