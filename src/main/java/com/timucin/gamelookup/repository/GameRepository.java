package com.timucin.gamelookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
