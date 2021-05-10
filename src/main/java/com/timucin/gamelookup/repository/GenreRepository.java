package com.timucin.gamelookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	
}
