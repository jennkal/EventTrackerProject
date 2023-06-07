package com.skilldistillery.logthings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.logthings.entities.Log;
import com.skilldistillery.logthings.repositories.LogThingsRepository;

@Service
public class LogThingsServiceImpl implements LogThingsService {
	
	@Autowired
	private LogThingsRepository logRepo;

	@Override
	public List<Log> listAllLogs() {
		return logRepo.findAll();
	}

	@Override
	public Log getLog(int logId) { 
		Log log = null;
		Optional<Log> logOpt = logRepo.findById(logId);
		if (logOpt.isPresent()) {
			log = logOpt.get();
		}
		return log;
	}

	@Override
	public Log create(Log newLog) {
		return logRepo.saveAndFlush(newLog);
	}

	@Override
	public Log update(Log log, int logId) {
		Optional<Log> logOpt = logRepo.findById(logId);
		if(logOpt.isPresent()) {
			//log.setTrip(logOpt.get());
			return logRepo.saveAndFlush(log);
		}
		return null;
	}

	@Override
	public boolean delete(int logId) {
		logRepo.deleteById(logId);
		return !logRepo.existsById(logId);
	}

}
