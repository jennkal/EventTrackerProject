package com.skilldistillery.logthings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.logthings.entities.MaintenanceLog;

public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Integer>{

	boolean existsById(int logId);
}
