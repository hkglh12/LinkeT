package com.project.Link.RegUser.Noticement.NoticementService;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.Link.Posting.Posting;
import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDao;
import com.project.Link.RegUser.Noticement.NoticementDao.NoticementDaoImple;
import com.project.Link.Ufile.Dao.UfileDao;
import com.project.Link.Ufile.Service.UfileService;

//@Service
public class NoticementServiceImplebkup /* implements NoticementService */ {
	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(NoticementServiceImplebkup.class); private int
	 * pagePerBlock = 8; private static final String targetBoard = "noticement";
	 * private static final String targetBoardFile = targetBoard+"file"; private
	 * static final String nFilePath = "C:\\temp\\" + targetBoard + "\\"; private
	 * String prefix = "n_";
	 * 
	 * @Autowired private NoticementDao nDao;
	 * 
	 * @Autowired private UfileService ufService;
	 * 
	 * public NoticementServiceImplebkup () {}; public NoticementDao getnDao()
	 * {return nDao;} public void setnDao(NoticementDao nDao) {this.nDao = nDao;}
	 * 
	 * @Override public int totalCountNoticements() { int totalCount =
	 * nDao.getTotalCount(targetBoard, prefix); return totalCount; }
	 * 
	 * @Override public ArrayList<Noticement> listNoticements(int targetPage) {
	 * logger.info("			ServiceLevelCalled ::::::: listNoticements");
	 * ArrayList<Noticement> list = nDao.getListNoticement(targetPage,pagePerBlock);
	 * return list; }
	 * 
	 * @Transactional(rollbackFor = RuntimeException.class)
	 * 
	 * @Override //public boolean createNoticement(String usrId, String ntcTitle,
	 * String ntcContents, List<MultipartFile> ufilelist) { public boolean
	 * createNoticement(String usrId, String title, String contents,
	 * List<MultipartFile> uFileList) throws Exception{
	 * logger.info("			ServiceLevelCalled ::::::: createNoticement");
	 * Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
	 * Iterator<MultipartFile> iterator = uFileList.iterator();
	 * while(iterator.hasNext()) { MultipartFile mf = iterator.next();
	 * if(mf.getOriginalFilename() == "" || mf.getOriginalFilename() == null){
	 * iterator.remove(); } } int serial = nDao.getLastSerial(targetBoard,
	 * prefix)+1; boolean result; try { result = nDao.createPosting(targetBoard,
	 * prefix, serial, usrId, title, contents, uFileList.size(), createDate) >=1 ?
	 * true : false; File file = new File(nFilePath); if(file.exists() == false){
	 * file.mkdirs(); } for(MultipartFile mf : uFileList) { String originalFileName
	 * = mf.getOriginalFilename(); long fileSize = mf.getSize(); String
	 * modifiedFileName = System.currentTimeMillis()+originalFileName; try {
	 * mf.transferTo(new File(nFilePath+modifiedFileName)); //uFileService는 Notice,
	 * Community에서 공통적으로 사용하기때문에, targetboard를 명시해야합니다. result =
	 * ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize,
	 * createDate, originalFileName, serial) >= 1 ? true : false;
	 * }catch(RuntimeException e) { throw e; }catch(IOException e) { throw e; } } }
	 * catch (Exception e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); throw e1; } return result; }
	 * 
	 * //Get 요청시 파일정보도 같이줘야합니다.
	 * 
	 * @Override public Noticement getNoticement(int targetSerial) {
	 * logger.info("			ServiceLevelCalled ::::::: getNoticement called");
	 * Noticement targetNoticement = nDao.getNoticement(targetSerial);
	 * nDao.countUp(targetBoard, prefix, targetSerial,
	 * targetNoticement.getReadCount()+1);
	 * targetNoticement.setReadCount(targetNoticement.getReadCount() +1);
	 * targetNoticement.setuFileList(ufService.uFileGet(targetBoardFile,
	 * targetSerial));
	 * 
	 * return targetNoticement; }
	 * 
	 * @Transactional(rollbackFor = RuntimeException.class)
	 * 
	 * @Override
	 * 
	 * public boolean updateNoticement(int targetSerial, String ntcTitle, String
	 * ntcContents, int fileCount) {
	 * 
	 * public boolean updateNoticement( String usrId, int serial, String title,
	 * String contents, List<String> previousFileCodes, List<String>
	 * deleteFileCodes, List<MultipartFile> uFileList ) throws Exception{
	 * logger.info("			ServiceLevelCalled ::::::: UpdateNoticement Called"
	 * ); try { //Integer.valueOf((String)mpRequest.getParameter("f_count"));
	 * Timestamp modifyDate = Timestamp.valueOf(LocalDateTime.now()); Timestamp
	 * createDate = Timestamp.valueOf(LocalDateTime.now());
	 * 
	 * int previousListSize = 0; int ufileListSize = 0; int deleteTargetSize = 0;
	 * 
	 * if(previousFileCodes != null) { Iterator<String> iterator =
	 * previousFileCodes.iterator(); while(iterator.hasNext()) { String smf =
	 * iterator.next(); if(smf==null || smf=="" || smf.isEmpty()){
	 * iterator.remove(); } } previousListSize = previousFileCodes.size();
	 * System.out.println("prev : " + previousListSize); } if(deleteFileCodes!=null)
	 * { Iterator<String> iterator = deleteFileCodes.iterator();
	 * System.out.println("deletefilecoesfiaaa : " + deleteFileCodes.size());
	 * Timestamp disconnDate = Timestamp.valueOf(LocalDateTime.now());
	 * while(iterator.hasNext()) { String smf = iterator.next(); if(smf==null ||
	 * smf=="" || smf.isEmpty()){ iterator.remove(); }else { // 파일 연결 바로 끊어버리는중
	 * logger.info("			ServiceLevelCalled ::::::: uFileDetach Called");
	 * ufService.uFileDetach(targetBoardFile, smf, usrId, disconnDate); } }
	 * deleteTargetSize = deleteFileCodes.size();
	 * System.out.println("del targetsize : " +deleteTargetSize); }
	 * if(uFileList!=null) { Iterator<MultipartFile> iterator =
	 * uFileList.iterator(); File file = new File(nFilePath); if(file.exists() ==
	 * false){ file.mkdirs(); } while(iterator.hasNext()) { MultipartFile mf =
	 * iterator.next(); if(mf.getOriginalFilename() == "" ||
	 * mf.getOriginalFilename() == null){ iterator.remove(); }else { String
	 * originalFileName = mf.getOriginalFilename(); long fileSize = mf.getSize();
	 * String modifiedFileName = System.currentTimeMillis()+originalFileName; try {
	 * //바로 파일 받고, 파일등록 mf.transferTo(new File(nFilePath+modifiedFileName));
	 * logger.info("			ServiceLevelCalled ::::::: uFileUpload Called");
	 * ufService.uFileUpload(targetBoardFile, modifiedFileName, usrId, fileSize,
	 * createDate, originalFileName, serial); }catch(RuntimeException e) { throw e;
	 * }catch(IOException e) { throw e; } } } ufileListSize = uFileList.size(); }
	 * logger.
	 * info("			ServiceLevelCalled ::::::: UploadNoticementCalled Finally Called"
	 * ); int fileCount = previousListSize - deleteTargetSize + ufileListSize;
	 * nDao.updateNoticement(usrId, serial, title, contents, fileCount, modifyDate);
	 * }catch(Exception e) { e.printStackTrace(); } return false; }
	 * 
	 * @Override public boolean deleteNoticement(int targetSerial, String usrId) {
	 * 
	 * Timestamp deleteDate = Timestamp.valueOf(LocalDateTime.now());
	 * 
	 * boolean result = nDao.deleteNoticement(usrId, targetSerial, deleteDate) >= 1
	 * ? true : false; return result; }
	 * 
	 * 
	 */
}
