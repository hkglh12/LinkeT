package com.project.Link.Admin.Manage.ServiceLog.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Link.Admin.Manage.ServiceLog.ServiceLog;
import com.project.Link.Admin.Manage.ServiceLog.Dao.ManageServiceLogDao;

@Service
public class ManageServiceLogServiceImple implements ManageServiceLogService{
	protected int logsperblock = 20;

	@Autowired
	private ManageServiceLogDao mslDao;
	
	@Override
	public int totalCountLogs(HashMap<String, Object> params) {
		String baseSql = "select count(*) as count from servicelevellog";
		// param이 비어있지 않다면
		if(!params.isEmpty()) baseSql += " where";
		int i=0;
		for(Map.Entry<String, Object> et : params.entrySet()) {
			if(et.getKey().contains("Date")) {
				// 날짜 관련이고

				// endDate의 경우 input ="date"는 00:00:00.0 이므로, 23:99:99로 변경하여 그날 전체를 포함하도록 변조한다.
				// 또한 얘네 '' 로 묶어줘야 인식됌.
				
				if(et.getKey().contains("start")) baseSql += " "+"occurtime" +" >= " + "'"+et.getValue()+"'"; // 시작이면
				else {	// 혹은 종료 날짜이면
					String temp = et.getValue().toString();
					temp = temp.substring(0,temp.indexOf(" ")) + " 23:59:59";
					baseSql += " " + "occurtime" + " <= " + "'"+temp+"'";
				}
				// 끝이면
			}else {
				baseSql += " " + et.getKey() + " like " + "'%"+et.getValue()+"%'";
			}
			// Idx 직접 조정
			i++;
			// 더있으면
			if(i<params.size()) baseSql += " and ";
		}
		return mslDao.getTotalCount(baseSql);
	}

	// 검색조건에 따라 DAO를 분화하는것이 아닌, Service에서 Sql문을 만들어서 내려주는것을 시도
	// SQL문 최초 where 추가를 위해서, (하드코딩이 아닌 자동화를 위해서) 컨트롤러에서 HashMapType으로 내려받음
	@Override
	public ArrayList<ServiceLog> getLogList(HashMap<String, Object> params, int targetPage) {
		ArrayList<ServiceLog> list = null;
		String baseSql = "select * from servicelevellog";
		// param이 비어있지 않다면
		
		if(!params.isEmpty()) baseSql += " where";
		int i=0;
		for(Map.Entry<String, Object> et : params.entrySet()) {
			if(et.getKey().contains("Date")) {
				// 날짜 관련이고
				// StartDate의 경우 input ="date"는 00:00:00.0 이므로, 23:99:99로 변경하여 그날 전체를 포함하도록 변조한다.
				// 또한 얘네 '' 로 묶어줘야 인식됌.
				if(et.getKey().contains("start")) baseSql += " "+ "occurtime" +" >= " + "'"+et.getValue()+"'"; // 키워드가 시작 이면
				else {		// 혹은 끝이면
					String temp = et.getValue().toString();
					temp = temp.substring(0,temp.indexOf(" ")) + " 23:59:59";
					baseSql += " " + "occurtime" + " <= " + "'"+temp+"'";
				}
				// 끝이면
			}else {
				baseSql += " "+ et.getKey() + " like " + "'%"+et.getValue()+"%'";
			}
			i++;
			// 더있으면
			if(i<params.size()) baseSql += " and ";
		}
		baseSql += " order by occurtime desc limit "+(targetPage*logsperblock) +", "+logsperblock;
		list = mslDao.getLogs(baseSql);
		return list;
	}
	
}
