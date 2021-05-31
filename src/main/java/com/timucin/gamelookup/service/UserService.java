package com.timucin.gamelookup.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.dto.UserDto;
import com.timucin.gamelookup.exception.UserAlreadyExistsException;
import com.timucin.gamelookup.repository.UserRepository;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User registerNewUserAccount(UserDto userDto) {
		User potentialUser = userRepository.findByUsername(userDto.getUsername());
	
		if(potentialUser != null) {
			throw new UserAlreadyExistsException("The user " + userDto + " already exists");
		}
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
        
		return userRepository.save(user);
	}
	

}
