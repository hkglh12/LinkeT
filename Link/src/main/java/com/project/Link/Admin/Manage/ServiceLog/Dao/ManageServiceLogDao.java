package com.project.Link.Admin.Manage.ServiceLog.Dao;

import java.util.ArrayList;

import com.project.Link.Admin.Manage.ServiceLog.ServiceLog;

public interface ManageServiceLogDao {
	public int getTotalCount(String baseSql);
	public ArrayList<ServiceLog> getLogs(String baseSql);
}
