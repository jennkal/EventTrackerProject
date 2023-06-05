package com.skilldistillery.logthings.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.logthings.entities.Log;
import com.skilldistillery.logthings.services.LogThingsService;

@RestController
@RequestMapping("api")
public class LogThingsController {
	
	@Autowired
	private LogThingsService logService;
	
	@GetMapping("logs")
	public List<Log> listAllLogs() {
		return logService.listAllLogs();
	}
	
	@GetMapping("logs/{logId}")
	public Log getLog(@PathVariable Integer logId, HttpServletResponse res) {
		Log log = logService.getLog(logId);
		if (log == null) {
			res.setStatus(404);
		}
		return log;
	}

}
