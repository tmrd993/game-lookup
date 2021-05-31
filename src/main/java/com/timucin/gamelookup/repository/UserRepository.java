package com.timucin.gamelookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	public User findByUsername(String username);

}
