package com.timucin.gamelookup.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.repository.UserRepository;

@Service
public class AuthenticatedUserService implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(AuthenticatedUserService.class);

	private final UserRepository userRepository;
	
	@Autowired
	public AuthenticatedUserService(UserRepository userRepository) {
		this.userRepository = userRepository;	
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("The user " + username + " does not exist.");
		}
		
		logger.info("Found user with username: " + user.getUsername());
		
		return new AuthenticatedUser(user);
	}

}
