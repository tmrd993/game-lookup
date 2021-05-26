package com.timucin.gamelookup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	public Optional<Genre> findByName(String name);
}
