package com.rsolucoes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsolucoes.model.User;

@RestController
public class UserController {

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		return user;
	}
}
