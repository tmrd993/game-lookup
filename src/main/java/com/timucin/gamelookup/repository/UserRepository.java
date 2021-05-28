package com.timucin.gamelookup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);

}
