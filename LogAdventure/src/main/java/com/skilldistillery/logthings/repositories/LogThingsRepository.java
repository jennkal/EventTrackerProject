package com.skilldistillery.logthings.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.logthings.entities.Log;

public interface LogThingsRepository extends JpaRepository<Log, Integer>{
	
//	List<Log> findById(int logId);
	boolean existsById(int logId);
}
