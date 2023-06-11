package com.skilldistillery.logthings.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.logthings.entities.ActivityLog;
import com.skilldistillery.logthings.entities.MaintenanceLog;
import com.skilldistillery.logthings.services.MaintenanceLogService;

@RestController
@RequestMapping("api")
public class MaintenanceLogController {

	@Autowired
	private MaintenanceLogService mainLogService;
	
	@GetMapping("main")
	public List<MaintenanceLog> listAllLogs() {
		return mainLogService.listAllMainLogs();
	}
	
	@GetMapping("mains/{logId}")
	public MaintenanceLog getLog(@PathVariable Integer logId, HttpServletResponse res) {
		MaintenanceLog mainLog = mainLogService.getLog(logId);
		if (mainLog == null) {
			res.setStatus(404);
		}
		return mainLog;
	}
	
	@PostMapping("mains")
	public MaintenanceLog create(@RequestBody MaintenanceLog newLog, HttpServletResponse res) {
		MaintenanceLog created = mainLogService.create(newLog);
		if (created != null) {
			res.setStatus(201);
		}
		return created;
	}
	
	@PutMapping("mains/{id}")
	public MaintenanceLog update(@RequestBody MaintenanceLog mainLog, @PathVariable int logId, HttpServletResponse res) {
		MaintenanceLog updated = mainLogService.update(mainLog, logId);
		return updated;
	}
	
	@DeleteMapping("mains/{id}")
	public void delete(@PathVariable int logId, HttpServletResponse res) {
		boolean deleted = mainLogService.delete(logId);
		if (deleted) {
			res.setStatus(204);
		}
	}
}
