package com.rsolucoes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsolucoes.model.User;
import com.rsolucoes.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserRepository userRepository;

	@PostMapping("/users")
	public User createUser(@RequestBody User user) throws Exception{
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if (isExist != null) {
			throw new Exception("user is exist whith email " + user.getEmail());
		}
		
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
//	public User findByEmail(String email) throws Exception{
//		User user = userRepository.findByEmail(email);
//		if (user == null) {
//			throw new Exception("user not found whith email " + email);
//		}
//		return user;
//	}
}
