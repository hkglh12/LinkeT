package com.project.Link.UserController;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UserController {
	/* 회원가입시 이미 동일한게 있는지 확인 */
	public HashMap<String, String> usrJoinValidation(@RequestBody HashMap<String,String> target, HttpServletRequest request);
	/* 유저 가입 */
	public String RegisterUser(Model model, HttpServletRequest request, RedirectAttributes attributes);
	/* 유저 조회 (로그인, 마이페이지 공통) */
	public String GetUser(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes);
	public String GetMe(Model model, HttpServletRequest request, HttpSession session);
	/* 유저정보변경 */
	public String UpdateUser(Model model, HttpServletRequest request, HttpSession session);
	/* 유저탈퇴 */
	public String DeleteUser(Model model, HttpServletRequest request, HttpSession session);
	public String LogOut(Model model, HttpServletRequest request, HttpSession session);
}
