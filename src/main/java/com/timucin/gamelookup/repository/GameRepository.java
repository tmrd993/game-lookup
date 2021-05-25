package com.timucin.gamelookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
	public List<Game> findByName(String name);

}
