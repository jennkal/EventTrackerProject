package com.skilldistillery.logthings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.logthings.entities.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer>{

	boolean existsById(int logId);
}
