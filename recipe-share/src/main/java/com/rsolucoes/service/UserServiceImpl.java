package com.rsolucoes.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rsolucoes.model.User;
import com.rsolucoes.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	@Override
	public User findUserById(Long id) throws Exception{
		
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new Exception ("User not found.");
	}

}
