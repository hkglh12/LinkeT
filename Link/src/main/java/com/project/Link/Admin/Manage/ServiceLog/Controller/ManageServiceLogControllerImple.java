package com.project.Link.Admin.Manage.ServiceLog.Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.Link.Admin.Manage.ServiceLog.ServiceLog;
import com.project.Link.Admin.Manage.ServiceLog.Service.ManageServiceLogService;

@RequestMapping(value="/admin/manage/log")
@Controller
public class ManageServiceLogControllerImple implements ManageServiceLogController{

	@Autowired
	private ManageServiceLogService mslService;
	
	public ManageServiceLogService getMslService() {return mslService;}
	public void setMslService(ManageServiceLogService mslService) {this.mslService = mslService;}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Override
	public String ListServiceLog(Model model, HttpServletRequest request, HttpSession session) {
		HashMap <String, Object> params = new HashMap<String,Object>();
		// DAO가 인자만큼 분화되는것을 막기위해서, Service에서 SQL문을 작성해서 DAO로 내릴 예정
		// service 에서 Iter로 처리하기 위해 HashMap으로 처리
		int targetPage = request.getParameter("page") == null || request.getParameter("page").equals("") ? 0 : Integer.parseInt(request.getParameter("page"))-1;
		Timestamp startDate = request.getParameter("start_date") == null || request.getParameter("start_date").equals("") ? null : new Timestamp(Date.valueOf((request.getParameter("start_date").toString())).getTime());
		if(startDate!=null) params.put("startDate",startDate);
		Timestamp endDate = request.getParameter("end_date") == null || request.getParameter("end_date").equals("") ? null : new Timestamp(Date.valueOf((request.getParameter("end_date").toString())).getTime());
		if(endDate!=null) params.put("endDate", endDate);
		String searchCategory = request.getParameter("search_category") == null || request.getParameter("search_category").equals("") ? null : request.getParameter("search_category");
		String searchTarget = request.getParameter("search_target") == null || request.getParameter("search_target").equals("") ? null : request.getParameter("sesarch_target");
		if(searchCategory != null && searchTarget != null) params.put(searchCategory, searchTarget);
		
		int total = mslService.totalCountLogs(params);
		ArrayList<ServiceLog> list = mslService.getLogList(params, targetPage);
		model.addAttribute("total", total);
		model.addAttribute("list", list);
		model.addAttribute("start_date", startDate);
		model.addAttribute("end_date", endDate);
		model.addAttribute("search_category", searchCategory);
		model.addAttribute("search_target", searchTarget);
		model.addAttribute("page", targetPage+1);
		return "/Admin/manage/log/board";
	}

}
