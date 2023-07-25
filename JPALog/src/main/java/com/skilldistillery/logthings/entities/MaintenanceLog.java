package com.skilldistillery.logthings.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
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
@Table(name="maintenance_log")
public class MaintenanceLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="adventure_log_id")
	private Log logId;
	
	private String description;
	
	@Column(name="maintenance_item")
	private String maintenanceItem;
	
	private String interval;
	
	private String fixes;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "maintenanceLogs")
	private List<User> users;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "mainLogs")
	private List<Log> logs;

	public MaintenanceLog() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Log getLogId() {
		return logId;
	}

	public void setLogId(Log logId) {
		this.logId = logId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaintenanceItem() {
		return maintenanceItem;
	}

	public void setMaintenanceItem(String maintenanceItem) {
		this.maintenanceItem = maintenanceItem;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getFixes() {
		return fixes;
	}

	public void setFixes(String fixes) {
		this.fixes = fixes;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		if (!users.contains(user)) {
			users.add(user);
			user.addMaintenanceLog(this);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
			user.removeMaintenanceLog(this);
		}
	}

	public void addLog(Log log) {
		if (logs == null) {
			logs = new ArrayList<>();
		}
		if (!logs.contains(log)) {
			logs.add(log);
			log.addMainLog(this);
		}
	}

	public void removeLog(Log log) {
		if (logs != null && logs.contains(log)) {
			logs.remove(log);
			log.removeMainLog(this);
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
		return "MaintenanceLog [id=" + id + ", logId=" + logId + ", description=" + description + ", maintenanceItem="
				+ maintenanceItem + ", interval=" + interval + ", fixes=" + fixes + "]";
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
		MaintenanceLog other = (MaintenanceLog) obj;
		return id == other.id;
	}

	
	
}
