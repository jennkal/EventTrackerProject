package com.skilldistillery.logthings.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="adventure_log")
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String category;
	
	private String details;
	
	private int duration;
	
	private String activity;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "adventureLogs")
	private List<User> users;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "adventure_log_has_activity_log",
	joinColumns = @JoinColumn(name="adventure_log_id"),
	inverseJoinColumns = @JoinColumn(name="activity_log_id"))
	private List<ActivityLog> actLogs;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="adventure_log_has_maintenance_log",
	joinColumns = @JoinColumn(name="adventure_log_id"),
	inverseJoinColumns = @JoinColumn(name="maintenance_log_id"))
	private List<MaintenanceLog> mainLogs;

	public Log() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<ActivityLog> getActLogs() {
		return actLogs;
	}

	public void setActLogs(List<ActivityLog> actLogs) {
		this.actLogs = actLogs;
	}

	public List<MaintenanceLog> getMainLogs() {
		return mainLogs;
	}

	public void setMainLogs(List<MaintenanceLog> mainLogs) {
		this.mainLogs = mainLogs;
	}
	
	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		if (!users.contains(user)) {
			users.add(user);
			user.addLog(this);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
			user.removeLog(this);
		}
	}
	
	public void addActLog(ActivityLog activityLog) {
		if (actLogs == null) {
			actLogs = new ArrayList<>();
		}
		if (!actLogs.contains(activityLog)) {
			actLogs.add(activityLog);
			activityLog.addLog(this);
		}
	}

	public void removeActLog(ActivityLog activityLog) {
		if (actLogs != null && actLogs.contains(activityLog)) {
			actLogs.remove(activityLog);
			activityLog.removeLog(this);
		}
	}
	
	public void addMainLog(MaintenanceLog maintenanceLog) {
		if(mainLogs == null) {
			mainLogs = new ArrayList<>();
		}
		if(!mainLogs.contains(maintenanceLog)) {
			mainLogs.add(maintenanceLog);
			maintenanceLog.addLog(this);
		}
	}
	
	public void removeMainLog(MaintenanceLog maintenanceLog) {
		if (mainLogs != null && mainLogs.contains(maintenanceLog)) {
			mainLogs.remove(maintenanceLog);
			maintenanceLog.removeLog(this);
		}
	}
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", category=" + category + ", details=" + details + ", duration=" + duration
				+ ", activity=" + activity + "]";
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
		Log other = (Log) obj;
		return id == other.id;
	}
}
