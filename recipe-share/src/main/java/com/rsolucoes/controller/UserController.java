package com.rsolucoes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/users")
	public List<User> getAllUsers() throws Exception{
		List<User> users = userRepository.findAll();
		return users;
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) throws Exception{
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if (isExist != null) {
			throw new Exception("user is exist whith email " + user.getEmail());
		}
		
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id) throws Exception{
		
		userRepository.deleteById(id);
		
		return "User deleted successfully";
	}
	
}
