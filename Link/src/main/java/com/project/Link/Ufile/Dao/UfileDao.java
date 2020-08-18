package com.project.Link.Ufile.Dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.project.Link.Ufile.UFile;

public interface UfileDao {
	// 특정 유저가 업로드한 파일 개수
	public int getUserFileCount(String usrId);
	// 파일 업로드
	public int uploadFile(String targetBoard, String modifiedFileName, String usrId, long fileSize, Timestamp createDate, String originalFileName, int serial);
	// 파일 리스트 Get
	public ArrayList<UFile> getFileList(String targetBoard, int relativeSerial);
	// 
	//public UFile getFile(String targetBoard, String fileCode);
	public boolean uFileCodeValidate(String targetBoard, String fileCode);
	// 파일 분리
	public void detachFile(String targetBoard, String targetCode, String usrId, Timestamp disconnDate);
}
