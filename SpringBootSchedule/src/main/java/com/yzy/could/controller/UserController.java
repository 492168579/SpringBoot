package com.yzy.could.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.could.entity.User;
import com.yzy.could.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		return null ;
	}
	@PostMapping("/user")
	public void insert(User user) {
		userService.insert(user);
	}
	@DeleteMapping("/user/{id}")
	public void delete(User user) {
		userService.insert(user);
	}

}
