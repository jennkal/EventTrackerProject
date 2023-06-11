package com.skilldistillery.logthings.services;

import java.util.List;

import com.skilldistillery.logthings.entities.MaintenanceLog;

public interface MaintenanceLogService {

	List<MaintenanceLog> listAllMainLogs();
	MaintenanceLog getLog(int logId);
	MaintenanceLog create(MaintenanceLog newLog);
	MaintenanceLog update(MaintenanceLog mainLog, int logId);
	boolean delete(int logId);
}
