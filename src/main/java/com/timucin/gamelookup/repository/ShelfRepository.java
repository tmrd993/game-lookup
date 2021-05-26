package com.timucin.gamelookup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timucin.gamelookup.domain.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
	public Optional<Shelf> findByName(String name);
}
