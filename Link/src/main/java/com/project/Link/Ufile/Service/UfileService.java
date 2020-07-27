package com.project.Link.Ufile.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.project.Link.Ufile.UFile;

public interface UfileService {
	public int uFileUpload(String targetBoard, String modifiedFileName,String usrId,long fileSize,Timestamp createDate, String originalFileName, int serial);
	public ArrayList<UFile> uFileGet(String targetBoard, int targetSerial); 
}
