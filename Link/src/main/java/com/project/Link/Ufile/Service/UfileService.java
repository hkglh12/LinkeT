package com.project.Link.Ufile.Service;

import java.sql.Timestamp;
import java.util.ArrayList;


import com.project.Link.Ufile.UFile;

public interface UfileService {
	// 특정 유저가 올린 파일 갯수를 리턴
	public int getUserFileCount(String usrId);
	// 파일 업로드
	public int uFileUpload(String targetBoard, String modifiedFileName,String usrId,long fileSize,Timestamp createDate, String originalFileName, int serial);
	// 파일 다운로드시 유효성 검증
	public boolean uFileValidate(String targetBoard, String fileCode);
	// 파일 리스트를 리턴
	public ArrayList<UFile> uFileGet(String targetBoard, int targetSerial);
	// 파일 삭제 (개별삭제)
	public void uFileDetach(String targetBoard, String targetCode, String usrId, Timestamp disconnDate) throws Exception;
}
