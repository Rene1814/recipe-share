package com.rsolucoes.service;

import com.rsolucoes.model.User;

public interface UserService {
	
	public User findUserById(Long id) throws Exception;

}
