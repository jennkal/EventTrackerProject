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
import com.skilldistillery.logthings.services.ActivityLogService;

@RestController
@RequestMapping("api")
public class ActivityLogController {
	@Autowired
	private ActivityLogService actLogService;
	
	@GetMapping("activity")
	public List<ActivityLog> listAllLogs() {
		return actLogService.listAllActLogs();
	}
	
	@GetMapping("activities/{logId}")
	public ActivityLog getLog(@PathVariable Integer logId, HttpServletResponse res) {
		ActivityLog log = actLogService.getLog(logId);
		if (log == null) {
			res.setStatus(404);
		}
		return log;
	}
	
	@PostMapping("activities")
	public ActivityLog create(@RequestBody ActivityLog newLog, HttpServletResponse res) {
		ActivityLog created = actLogService.create(newLog);
		if (created != null) {
			res.setStatus(201);
		}
		return created;
	}
	
	@PutMapping("activities/{id}")
	public ActivityLog update(@RequestBody ActivityLog actLog, @PathVariable int logId, HttpServletResponse res) {
		ActivityLog updated = actLogService.update(actLog, logId);
		return updated;
	}
	
	@DeleteMapping("activities/{id}")
	public void delete(@PathVariable int logId, HttpServletResponse res) {
		boolean deleted = actLogService.delete(logId);
		if (deleted) {
			res.setStatus(204);
		}
	}
}
