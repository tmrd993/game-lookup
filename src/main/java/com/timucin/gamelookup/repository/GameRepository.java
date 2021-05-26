package com.timucin.gamelookup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	public Optional<Game> findByName(String name);

}
