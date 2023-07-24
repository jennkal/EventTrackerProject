package com.skilldistillery.logthings.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private boolean enabled;
	
	private String role;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "image_url")
	private String image;
	
	private String description;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_has_adventure_log", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "adventure_log_id"))
	private List<Log> adventureLogs;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_has_activity_log",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "activity_log"))
	private List<ActivityLog> activityLogs;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_has_maintenance_log",
	joinColumns = @JoinColumn(name ="user_id"),
	inverseJoinColumns = @JoinColumn(name = "maintenance_log_id"))
	private List<MaintenanceLog> maintenanceLogs;
	

	public List<ActivityLog> getActivityLogs() {
		return activityLogs;
	}

	public void setActivityLogs(List<ActivityLog> activityLogs) {
		this.activityLogs = activityLogs;
	}

	public List<MaintenanceLog> getMaintenanceLogs() {
		return maintenanceLogs;
	}

	public void setMaintenanceLogs(List<MaintenanceLog> maintenanceLogs) {
		this.maintenanceLogs = maintenanceLogs;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Log> getAdventureLogs() {
		return adventureLogs;
	}

	public void setAdventureLogs(List<Log> adventureLogs) {
		this.adventureLogs = adventureLogs;
	}
	
	public void addLog(Log adventureLog) {
		if (adventureLogs == null) {
			adventureLogs = new ArrayList<>();
		}
		if (!adventureLogs.contains(adventureLog)) {
			adventureLogs.add(adventureLog);
			adventureLog.addUser(this);
		}
	}

	public void removeLog(Log adventureLog) {
		if (adventureLogs != null && adventureLogs.contains(adventureLog)) {
			adventureLogs.remove(adventureLog);
			adventureLog.removeUser(this);
		}
	}
	
	public void addActivityLog(ActivityLog activityLog) {
		if (activityLogs == null) {
			activityLogs = new ArrayList<>();
		}
		if (!activityLogs.contains(activityLog)) {
			activityLogs.add(activityLog);
			activityLog.addUser(this);
		}
	}

	public void removeActivityLog(ActivityLog activityLog) {
		if (activityLogs != null && activityLogs.contains(activityLog)) {
			activityLogs.remove(activityLog);
			activityLog.removeUser(this);
		}
	}
	
	public void addMaintenanceLog(MaintenanceLog maintenanceLog) {
		if(maintenanceLogs == null) {
			maintenanceLogs = new ArrayList<>();
		}
		if(!maintenanceLogs.contains(maintenanceLog)) {
			maintenanceLogs.add(maintenanceLog);
			maintenanceLog.addUser(this);
		}
	}
	
	public void removeMaintenanceLog(MaintenanceLog maintenanceLog) {
		if (maintenanceLogs != null && maintenanceLogs.contains(maintenanceLog)) {
			maintenanceLogs.remove(maintenanceLog);
			maintenanceLog.removeUser(this);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", image=" + image
				+ ", description=" + description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
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
		User other = (User) obj;
		return id == other.id;
	}
}
