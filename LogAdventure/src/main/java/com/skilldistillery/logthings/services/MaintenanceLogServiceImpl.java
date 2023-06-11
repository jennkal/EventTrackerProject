package com.skilldistillery.logthings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.logthings.entities.MaintenanceLog;
import com.skilldistillery.logthings.repositories.MaintenanceLogRepository;

@Service
public class MaintenanceLogServiceImpl implements MaintenanceLogService {
	
	@Autowired
	private MaintenanceLogRepository mainLogRepo;

	@Override
	public List<MaintenanceLog> listAllMainLogs() {
		return mainLogRepo.findAll();
	}

	@Override
	public MaintenanceLog getLog(int logId) {
		MaintenanceLog mainLog = null;
		Optional<MaintenanceLog> logOpt = mainLogRepo.findById(logId);
		if(logOpt.isPresent()) {
			mainLog = logOpt.get();
		}
		return mainLog;
	}

	@Override
	public MaintenanceLog create(MaintenanceLog newLog) {
		return mainLogRepo.saveAndFlush(newLog);
	}

	@Override
	public MaintenanceLog update(MaintenanceLog mainLog, int logId) {
		Optional<MaintenanceLog> logOpt = mainLogRepo.findById(logId);
		if(logOpt.isPresent()) {
			return mainLogRepo.saveAndFlush(mainLog);
		}
		return null;
	}

	@Override
	public boolean delete(int logId) {
		mainLogRepo.deleteById(logId);
		return !mainLogRepo.existsById(logId);
	}

}
