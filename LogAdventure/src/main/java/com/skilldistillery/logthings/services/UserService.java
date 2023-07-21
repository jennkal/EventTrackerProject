package com.skilldistillery.logthings.services;

import java.util.List;

import com.skilldistillery.logthings.entities.User;

public interface UserService {
	
	List<User> index(String username);
	User show(String username, int id);
	User update(String username, int id, User user);
	//TODO Add method if necessary for disable/enable user
	User addLogToUser(String username, int lid);
	User findByUsername(String username);
	User removeLogFromUser(String username, int lid);
	boolean disableUser(int id);

}
