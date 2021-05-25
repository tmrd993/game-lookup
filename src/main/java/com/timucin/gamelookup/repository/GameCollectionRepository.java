package com.timucin.gamelookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.GameCollection;

public interface GameCollectionRepository extends JpaRepository<GameCollection, Long> {

}
