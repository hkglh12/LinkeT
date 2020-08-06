package com.project.Link.RegUser.Noticement.NoticementController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Link.RegUser.Noticement.Noticement;
import com.project.Link.RegUser.Noticement.NoticementService.NoticementService;
import com.project.Link.RegUser.Posting.Posting;
import com.project.Link.RegUser.User.UserController.UserControllerImple;
import com.project.Link.SessionControl.SessionControl;
import com.project.Link.Ufile.Service.UfileService;

//TODO NOTICEMENT POST JSP로 넘기고 연결 URL 생성

/*@RequestMapping(value="/notice/*")
@Controller*/
public class NoticementControllerImplebkup/* implements NoticementController */ {
	/*
	 * private final String nFilePath = "c:\\temp\\noticement\\"; private static
	 * final Logger logger =
	 * LoggerFactory.getLogger(NoticementControllerImplebkup.class);
	 * 
	 * @Autowired private NoticementService nService;
	 * 
	 * @Autowired private UfileService ufService;
	 * 
	 * @Autowired private SessionControl sc;
	 * 
	 * public NoticementControllerImplebkup() {} public NoticementService
	 * getnService() {return nService;} public void setnService(NoticementService
	 * nService) {this.nService = nService;} public SessionControl getSc() {return
	 * sc;} public void setSc(SessionControl sc) {this.sc = sc; }
	 * 
	 * 
	 * @RequestMapping(value="/form", method=RequestMethod.GET)
	 * 
	 * @Override public String getPostTemplate(Model model, HttpServletRequest
	 * reqeust, HttpSession session) { return "noticePost"; } //Service Level 통합 완료
	 * 
	 * @RequestMapping(value={"/list", ""}, method = RequestMethod.GET)
	 * 
	 * @Override public String ListNoticements(Model model, HttpServletRequest
	 * request, HttpSession session, RedirectAttributes redirectAttr) {
	 * logger.info("		Controller Level :: ListNoticements Called");
	 * HashMap<String, String> sr = sc.sessionControl(request, session,
	 * redirectAttr); if(sr.get("usrId")==null) { return "/"; }else { int total =
	 * nService.totalCountNoticements(); int targetPage =
	 * request.getParameter("page") == null ? 0
	 * :Integer.parseInt(request.getParameter("page"))-1; ArrayList<Noticement> list
	 * = nService.listNoticements(targetPage); model.addAttribute("total", total);
	 * model.addAttribute("noticelist",list); } return "noticeBoard"; }
	 * 
	 * @RequestMapping(value="/post", method = RequestMethod.POST)
	 * 
	 * @Override
	 * 
	 * public String PostNoticement(Model model, HttpServletRequest request,
	 * HttpSession session, RedirectAttributes redirectAttr) {
	 * 
	 * //Service레벨 통합 완료 public String PostNoticement(Model model,
	 * MultipartHttpServletRequest mpRequest, HttpSession session,
	 * RedirectAttributes redirectAttr) throws Exception{
	 * logger.info("		Controller Level :: PostNoticement Called");
	 * 
	 * HashMap<String, String> sr = sc.sessionControl(session);
	 * 
	 * if(sr.get("usrId")==null || sr.get("isAdmin")=="false") { return "failed";
	 * }else {
	 * 
	 * String usrId = (String)session.getAttribute("usrId"); String title =
	 * mpRequest.getParameter("n_title"); String contents =
	 * mpRequest.getParameter("n_contents"); List<MultipartFile> uFileList =
	 * mpRequest.getFiles("u_files");
	 * 
	 * nService.createNoticement(usrId, title, contents,uFileList);
	 * 
	 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
	 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
	 * 
	 * redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
	 * redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
	 * return "redirect:/notice/list?page=1"; } } //ServiceLevel 통합 완료
	 * 
	 * @RequestMapping(value="/get", method = RequestMethod.GET)
	 * 
	 * @Override public String GetNoticement(Model model, HttpServletRequest
	 * request, HttpSession session, RedirectAttributes redirecAttr) {
	 * logger.info("		Controller Level :: GetNoticement Called");
	 * 
	 * HashMap<String, String> sr = sc.sessionControl(session);
	 * if(sr.get("usrId")==null) { return "/"; }else {
	 * 
	 * int targetSerial = Integer.valueOf(request.getParameter("n_serial"));
	 * Noticement noticement = nService.getNoticement(targetSerial);
	 * 
	 * 
	 * model.addAttribute("n_serial", noticement.getSerial());
	 * model.addAttribute("u_id", noticement.getUsrId());
	 * model.addAttribute("n_title", noticement.getTitle());
	 * model.addAttribute("n_contents", noticement.getContents());
	 * model.addAttribute("f_count", noticement.getFileCount());
	 * model.addAttribute("n_createdate", noticement.getCreateDate());
	 * model.addAttribute("n_count",noticement.getNoticeCount());
	 * 
	 * model.addAttribute("noticement", noticement); return "noticeRead"; } }
	 * //ServiceLevel 통합 종료 //UpdateForm으로 이동할때 데이터를 재요청하기위한 곳
	 * 
	 * @RequestMapping(value="/update", method = RequestMethod.GET) public String
	 * getUpdateNoticement(Model model, HttpServletRequest request, HttpSession
	 * session) {
	 * logger.info("		Controller Level :: getUpdateNoticement Called");
	 * 
	 * HashMap<String, String> sr = sc.sessionControl(session);
	 * if(sr.get("usrId")==null) { return "/"; }else {
	 * 
	 * int targetSerial = Integer.valueOf(request.getParameter("n_serial"));
	 * Noticement noticement = nService.getNoticement(targetSerial);
	 * 
	 * model.addAttribute("n_serial", noticement.getSerial());
	 * model.addAttribute("u_id", noticement.getUsrId());
	 * model.addAttribute("n_title", noticement.getTitle());
	 * model.addAttribute("n_contents", noticement.getContents());
	 *
	 * 
	 * model.addAttribute("noticement",noticement); //이거 noticeUpdateForm으로 바꾸세여
	 * return "noticeUpdateForm"; } } //ServiceLevel 통합 시작 //통합보류 , 사유 : 파일 변경에 대한
	 * ISSUE
	 * 
	 * @RequestMapping(value="/update", method = RequestMethod.POST)
	 * 
	 * @Override public String UpdateNoticement(Model model,
	 * MultipartHttpServletRequest mpRequest, HttpSession session,
	 * RedirectAttributes redirectAttr) throws Exception {
	 * logger.info("		Controller Level :: UpdateNoticement Called");
	 * 
	 * HashMap<String, String> sr = sc.sessionControl(session);
	 * if(sr.get("usrId")==null || sr.get("isAdmin") == "false") { return "/"; }else
	 * {
	 * 
	 * String usrId = (String)session.getAttribute("usrId"); int serial =
	 * Integer.valueOf((String)mpRequest.getParameter("n_serial")); String title =
	 * mpRequest.getParameter("n_title"); String contents =
	 * mpRequest.getParameter("n_contents");
	 * 
	 * List<String> previousFileCodes = null;
	 * if(mpRequest.getParameterValues("previous") != null) { previousFileCodes =
	 * Arrays.asList(mpRequest.getParameterValues("previous")); }
	 * 
	 * List<String> deleteFileCodes = null;
	 * if(mpRequest.getParameterValues("del_targets")!=null) { deleteFileCodes =
	 * Arrays.asList(mpRequest.getParameterValues("del_targets")); }
	 * List<MultipartFile> uFileList = null; if(mpRequest.getFiles("u_files")!=null)
	 * { uFileList= mpRequest.getFiles("u_files"); }
	 * 
	 * boolean result = nService.updateNoticement(usrId, serial, title, contents,
	 * previousFileCodes, deleteFileCodes, uFileList);
	 * 
	 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
	 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
	 * 
	 * redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
	 * redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
	 * return "redirect:/notice/list?page=1"; } } //ServiceLevel 통합 종료
	 * 
	 * @RequestMapping(value="/delete", method = RequestMethod.POST)
	 * 
	 * @Override public String DeleteNoticement(Model model, HttpServletRequest
	 * request, HttpSession session, RedirectAttributes redirectAttr) {
	 * logger.info("		Controller Level :: DeleteNoticement Called");
	 * 
	 * HashMap<String, String> sr = sc.sessionControl(session);
	 * if(sr.get("usrId")==null || sr.get("isAdmin") == "false") { return "/"; }else
	 * {
	 * 
	 * int targetSerial = Integer.valueOf(request.getParameter("n_serial")); String
	 * usrId = (String)session.getAttribute("usrId"); boolean result =
	 * nService.deleteNoticement(targetSerial, usrId);
	 * 
	 * redirectAttr.addFlashAttribute("usrId", sr.get("usrId"));
	 * redirectAttr.addFlashAttribute("isAdmin", sr.get("isAdmin"));
	 * 
	 * redirectAttr.addFlashAttribute("usrId", session.getAttribute("usrId"));
	 * redirectAttr.addFlashAttribute("isAdmin", session.getAttribute("isAdmin"));
	 * return "redirect:/notice/list?page=1"; } }
	 * 
	 * @Override
	 * 
	 * @RequestMapping(value="/download", method=RequestMethod.GET) public void
	 * getNoticementFile(Model model,HttpServletRequest request, HttpSession
	 * session, HttpServletResponse response) throws Exception { //TODO 파일 유효한지
	 * 찾아야함. try { File file = new File(nFilePath+request.getParameter("fileCode"));
	 * if(file.exists()) { String mimeType = Files.probeContentType(file.toPath());
	 * if(mimeType==null) { mimeType="application/octet-stream"; }
	 * response.setContentType(mimeType);
	 * response.addHeader("Content-Disposition","attachment; filename=" +
	 * request.getParameter("fileCode"));
	 * response.setContentLength((int)file.length());
	 * 
	 * OutputStream os = response.getOutputStream(); FileInputStream fis = new
	 * FileInputStream(file); byte[] buffer = new byte[4096]; int b=-1;
	 * while((b=fis.read(buffer)) != -1) { os.write(buffer, 0, b); } fis.close();
	 * os.close(); }else {
	 * 
	 * } }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * HashMap<String, String> sr = sc.sessionControl(session);
	 * if(sr.get("usrId")==null) {
	 * 
	 * }else {
	 * 
	 * boolean result = nService.deleteNoticement(session, request); }
	 * 
	 * 
	 * }
	 * 
	 * Exception handler
	 * https://jaehun2841.github.io/2018/08/30/2018-08-25-spring-mvc-handle-
	 * exception/#global-%EB%A0%88%EB%B2%A8%EC%97%90%EC%84%9C%EC%9D%98-%EC%B2%98%EB%
	 * A6%AC
	 * 
	 * 
	 * @ExceptionHandler(value=Exception.class) public String
	 * handleDemoException(Exception e) { logger.error(e.getMessage());
	 * e.printStackTrace(); return "/error/404"; }
	 * 
	 * 
	 */
}
