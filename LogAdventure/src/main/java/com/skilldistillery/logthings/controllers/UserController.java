package com.skilldistillery.logthings.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.logthings.entities.User;
import com.skilldistillery.logthings.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http;//localhost/" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return userService.index(principal.getName());
	}

	@GetMapping("users/{uid}")
	public User show(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int uid) {
		User user = userService.show(principal.getName(), uid);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	@PutMapping("users/{uid}")
	public User update(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int uid,
			@RequestBody User user) {
		try {
			user = userService.update(principal.getName(), uid, user);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}
	
	@DeleteMapping("users/{uid}")
	public void disable(HttpServletResponse res, @PathVariable Integer uid) {
		if (userService.disableUser(uid)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

	@PutMapping("users/{pid}/parks")
	public User addParkToUserFavorites(HttpServletResponse res, Principal principal, @PathVariable int pid) {
			User loggedInUser = userService.findByUsername(principal.getName());
		try {
			loggedInUser = userService.addParkToUserFavorites(loggedInUser.getUsername(), pid);
			if (loggedInUser == null) {
				res.setStatus(404);
				return loggedInUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			loggedInUser = null;
		}
		return loggedInUser;

	}
	
	@PutMapping("users/parks/{pid}")
	public User removeParkFromUserFavorites(HttpServletResponse res, Principal principal, @PathVariable int pid) {
			User loggedInUser = userService.findByUsername(principal.getName());
		try {
			loggedInUser = userService.removeParkFromUserFavorites(loggedInUser.getUsername(), pid);
			if (loggedInUser == null) {
				res.setStatus(404);
				return loggedInUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			loggedInUser = null;
		}
		return loggedInUser;

	}
}
