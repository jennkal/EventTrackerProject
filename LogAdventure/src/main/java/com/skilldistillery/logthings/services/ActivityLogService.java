package com.skilldistillery.logthings.services;

import java.util.List;

import com.skilldistillery.logthings.entities.ActivityLog;

public interface ActivityLogService {
	
	List<ActivityLog> listAllActLogs();
	ActivityLog getLog(int logId);
	ActivityLog create(ActivityLog newLog);
	ActivityLog update(ActivityLog actLog, int logId);
	boolean delete(int logId);

}
