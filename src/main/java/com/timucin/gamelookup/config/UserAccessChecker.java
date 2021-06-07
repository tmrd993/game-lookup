package com.timucin.gamelookup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.repository.UserRepository;

@Component
public class UserAccessChecker {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserAccessChecker(UserRepository userRepository) {
		this.userRepository = userRepository;	
	}
	
	public boolean checkAccess(Authentication authentication, String username) {
		String name = authentication.getName();
		User user = userRepository.findByUsername(name);
		
		return user != null && user.getUsername().equals(username);
	}

}
