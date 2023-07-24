package com.skilldistillery.logthings.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.logthings.entities.Log;
import com.skilldistillery.logthings.entities.User;
import com.skilldistillery.logthings.repositories.LogThingsRepository;
import com.skilldistillery.logthings.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private LogThingsRepository logRepo;

	@Override
	public List<User> index(String username) {
		List<User> allUsers = null;
		User loggedInUser = userRepo.findByUsername(username);
		if (loggedInUser != null) {
			allUsers = userRepo.findAll();
		}
		return allUsers;
	}

	@Override
	public User show(String username, int id) {
		User otherUser = null;
		User loggedInUser = userRepo.findByUsername(username);
		if (loggedInUser != null) {
			otherUser = userRepo.findById(id);
		}
		return otherUser;
	}

	@Override // TODO MAY NOT NEED
	public User update(String username, int id, User user) {
		User loggedInUser = userRepo.findByUsername(username);
		if (loggedInUser != null) {
			User existingUser = userRepo.findById(id);
			if (existingUser != null) {
				if (user.getPassword() != null) {
					existingUser.setPassword(user.getPassword());
				}
				if (user.getFirstName() != null) {
					existingUser.setFirstName(user.getFirstName());
				}
				if (user.getLastName() != null) {
					existingUser.setLastName(user.getLastName());
				}
				if (user.getDescription() != null) {
					existingUser.setDescription(user.getDescription());
				}
				if (user.getImage() != null) {
					existingUser.setImage(user.getImage());
				}
				if (user.isEnabled() != existingUser.isEnabled()) {
					existingUser.setEnabled(user.isEnabled());
				}
				return userRepo.saveAndFlush(existingUser);
			}
			return null;
		}
		return null;
	}

	@Override
	public boolean disableUser(int id) {
		User userForDisable = userRepo.findById(id);
		boolean initialEnabled = userForDisable.isEnabled();
		userForDisable.setEnabled(!userForDisable.isEnabled());
		if (userForDisable.isEnabled() != initialEnabled) {
			userRepo.saveAndFlush(userForDisable);
			return true;
		}
		return false;
	}

	@Override
	public User addLogToUser(String username, int lid) {
		User loggedInUser = userRepo.findByUsername(username);
		Log existingLog = logRepo.findById(lid);
		
		if (existingLog != null) {
			List<Log> favoriteLogs = loggedInUser.getAdventureLogs();
			favoriteLogs.add(existingLog);
			loggedInUser.setAdventureLogs(favoriteLogs);
			logRepo.saveAndFlush(existingLog);
		
		}
		return loggedInUser;

	}

	@Override
	public User removeLogFromUser(String username, int lid) {
		User loggedInUser = userRepo.findByUsername(username);
		Log existingLog = logRepo.findById(lid);
		
		if (existingLog != null) {
			List<Log> favoriteLogs = loggedInUser.getAdventureLogs();
			favoriteLogs.remove(existingLog);
			loggedInUser.setAdventureLogs(favoriteLogs);
			logRepo.saveAndFlush(existingLog);
		}
		return loggedInUser;

	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	// TODO disable/enable if admin


}
