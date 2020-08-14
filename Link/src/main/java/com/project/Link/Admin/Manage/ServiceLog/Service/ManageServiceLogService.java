package com.project.Link.Admin.Manage.ServiceLog.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.Link.Admin.Manage.ServiceLog.ServiceLog;

public interface ManageServiceLogService {
	public int totalCountLogs(HashMap<String, Object> params);
	public ArrayList<ServiceLog> getLogList(HashMap<String, Object> params, int targetPage);
}
