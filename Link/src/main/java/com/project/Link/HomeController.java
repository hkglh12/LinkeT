package com.project.Link;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.Link.SessionControl.SessionControl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements HomeControllerIntf{
	public HomeController(){}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		return "/User/main/main";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) {
		return "signup";
	}
	
	/* 파일업로드 */
	/*
	 * http://wiki.gurubee.net/pages/viewpage.action?pageId=6261117
	 */	
	/*
	 * 에러페이지 https://jaehun2841.github.io/2018/08/30/2018-08-25-spring-mvc-handle-
	 * exception/#controller-%EB%A0%88%EB%B2%A8%EC%97%90%EC%84%9C%EC%9D%98-%EC%B2%98
	 * %EB%A6%AC
	 */
	/* 인터셉터
	 * https://gangnam-americano.tistory.com/11
	 */
	//TODO Service로 녹여내린 http, session정보 다시 꺼내야함.
	// 서비스에는 Id, String 등등만 써야한대요
}
