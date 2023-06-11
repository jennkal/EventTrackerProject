package com.skilldistillery.logthings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.logthings.entities.ActivityLog;
import com.skilldistillery.logthings.repositories.ActivityLogRepository;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
	
	@Autowired
	private ActivityLogRepository actLogRepo;

	@Override
	public List<ActivityLog> listAllActLogs() {
		return actLogRepo.findAll();
	}

	@Override
	public ActivityLog getLog(int logId) {
		ActivityLog actLog = null;
		Optional<ActivityLog> logOpt = actLogRepo.findById(logId);
		if(logOpt.isPresent()) {
			actLog = logOpt.get();
		}
		return actLog;
	}

	@Override
	public ActivityLog create(ActivityLog newLog) {
		return actLogRepo.saveAndFlush(newLog);
	}

	@Override
	public ActivityLog update(ActivityLog actLog, int logId) {
		Optional<ActivityLog> logOpt = actLogRepo.findById(logId);
		if(logOpt.isPresent()) {
			return actLogRepo.saveAndFlush(actLog);
		}
		return null;
	}

	@Override
	public boolean delete(int logId) {
		actLogRepo.deleteById(logId);
		return !actLogRepo.existsById(logId);
	}

}
