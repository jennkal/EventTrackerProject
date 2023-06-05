package com.skilldistillery.logthings.services;

import java.util.List;

import com.skilldistillery.logthings.entities.Log;

public interface LogThingsService {
	
	List<Log> listAllLogs();
	Log getLog(int logId);
	Log create(Log newLog);
	Log update(int logId);
	boolean delete(int logId);

}
