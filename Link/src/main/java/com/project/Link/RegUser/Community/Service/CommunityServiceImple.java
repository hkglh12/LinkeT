package com.project.Link.RegUser.Community.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.Link.Commons.Community.Service.CommonsCommunityServiceImple;
import com.project.Link.RegUser.Community.Dao.CommunityDao;
import com.project.Link.Ufile.Service.UfileService;

@Service
@Qualifier("UserCommunityService")
public class CommunityServiceImple extends CommonsCommunityServiceImple implements CommunityService {
	// 사용하는 변수 (테이블명, 파일경로, pagePerBlock, prefix는 Protected로 Commons로부터 상속받아서 사용)

	@Autowired
	@Qualifier("UserCommunityDao")
	private CommunityDao cDao;
	@Autowired
	private UfileService ufService;

	public CommunityServiceImple() {}
	public CommunityDao getcDao() {return cDao;}
	public void setcDao(CommunityDao cDao) {this.cDao = cDao;}
	public UfileService getUfService() {return ufService;}
	public void setUfService(UfileService ufService) {this.ufService = ufService;}

	@Transactional 
	@Override
	public boolean createCommunity(String usrId, String title, String contents, List<MultipartFile> uFileList, String subject) throws Exception {
		// 게시글 등록
		Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
		Iterator<MultipartFile> iterator = uFileList.iterator();
		while (iterator.hasNext()) {
			MultipartFile mf = iterator.next();
			if (mf.getOriginalFilename() == "" || mf.getOriginalFilename() == null) {
				iterator.remove();
			}
		}
		int serial = cDao.getLastSerial(targetBoard, prefix) + 1;
		boolean result;
		try {
			result = cDao.createPosting(targetBoard, prefix, serial, usrId, title, contents, uFileList.size(), createDate, subject) >= 1 ? true : false;
			File file = new File(cFilePath);
			if (file.exists() == false) {
				file.mkdirs();
			}
			for (MultipartFile mf : uFileList) {
				String originalFileName = mf.getOriginalFilename();
				long fileSize = mf.getSize();
				String modifiedFileName = System.currentTimeMillis() + originalFileName;
				try {
					mf.transferTo(new File(cFilePath + modifiedFileName));
					// uFileService는 Notice, Community에서 공통적으로 사용하기때문에, targetboard를 명시해야합니다.
					result = ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize, createDate,
							originalFileName, serial) >= 1 ? true : false;
				} catch (RuntimeException e) {
					throw e;
				} catch (IOException e) {
					throw e;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}
		return result;
	}
	// 게시글 수정
	@Transactional
	@Override
	public boolean updateCommunity(String usrId, int serial, String title, String contents, List<String> previousFileCodes, List<String> deleteFileCodes, List<MultipartFile> uFileList) throws Exception {
		try {
			Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now());
			Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
			int previousListSize = 0;
			int ufileListSize = 0;
			int deleteTargetSize = 0;
			//Multifile.js를 활성화하지 않았을때에도 , 활성화 했을때도 유효한 코드.
			// 중간에 "입력창"을 추가하고, 아무것도 첨부하지 않았을때 제거하는 코드.
			if (previousFileCodes != null) {
				Iterator<String> iterator = previousFileCodes.iterator();
				while (iterator.hasNext()) {
					String smf = iterator.next();
					if (smf == null || smf == "" || smf.isEmpty()) {
						iterator.remove();
					}
				}
				previousListSize = previousFileCodes.size();
			}
			if (deleteFileCodes != null) {
				Iterator<String> iterator = deleteFileCodes.iterator();
				Timestamp disconnDate = Timestamp.valueOf(LocalDateTime.now());
				while (iterator.hasNext()) {
					String smf = iterator.next();
					if (smf == null || smf == "" || smf.isEmpty()) {
						iterator.remove();
					} else {
						ufService.uFileDetach(targetBoardFile, smf, usrId, disconnDate);
					}
				}
				deleteTargetSize = deleteFileCodes.size();
			}
			if (uFileList != null) {
				Iterator<MultipartFile> iterator = uFileList.iterator();
				File file = new File(cFilePath);
				if (file.exists() == false) {
					file.mkdirs();
				}
				while (iterator.hasNext()) {
					MultipartFile mf = iterator.next();
					if (mf.getOriginalFilename() == "" || mf.getOriginalFilename() == null) {
						iterator.remove();
					} else {
						String originalFileName = mf.getOriginalFilename();
						long fileSize = mf.getSize();
						String modifiedFileName = System.currentTimeMillis() + originalFileName;
						
						try {
							mf.transferTo(new File(cFilePath + modifiedFileName));
							ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize, createDate,
									originalFileName, serial);
						} catch (RuntimeException e) {
							throw e;
						} catch (IOException e) {
							throw e;
						}
					}
				}
				ufileListSize = uFileList.size();
			}
			int fileCount = previousListSize - deleteTargetSize + ufileListSize;
			// 파일 자체의 업데이트를 끝내면 filecount 변조, 수정날짜 변조
			cDao.updateCommunity(serial, title, contents, fileCount, modifyDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteCommunity(int targetSerial) {
		// 게시글 삭제요청
		Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
		boolean result = cDao.deleteCommunity(targetSerial, deleteDate) >= 1 ? true : false;
		return result;
	}

}
