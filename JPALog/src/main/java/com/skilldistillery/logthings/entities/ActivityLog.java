package com.skilldistillery.logthings.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="activity_log")
public class ActivityLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="adventure_log_id")
	private Log log;
	
	private String name;
	
	private int distance;
	
	private String details;

	
	@JsonIgnore
	@ManyToMany(mappedBy = "actLogs")
	private List<Log> logs;

	public ActivityLog() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	
	public void addLog(Log log) {
		if (logs == null) {
			logs = new ArrayList<>();
		}
		if (!logs.contains(log)) {
			logs.add(log);
			log.addActLog(this);
		}
	}

	public void removeLog(Log log) {
		if (logs != null && logs.contains(log)) {
			logs.remove(log);
			log.removeActLog(this);
		}
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "ActivityLog [id=" + id + ", log=" + log + ", name=" + name + ", distance=" + distance + ", details="
				+ details + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityLog other = (ActivityLog) obj;
		return id == other.id;
	}
	
	

}
