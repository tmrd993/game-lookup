package com.timucin.gamelookup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.repository.ShelfRepository;

@Service
public class ShelfService {
	
	private final ShelfRepository shelfRepository;
	
	@Autowired
	public ShelfService(ShelfRepository shelfRepository) {
		this.shelfRepository = shelfRepository;
	}
	
	public List<Shelf> findAll() {
		return shelfRepository.findAll();
	}
	
	public Optional<Shelf> findById(Long id) {
		return shelfRepository.findById(id);
	}
	
	public Shelf save(Shelf shelf) {
		return shelfRepository.save(shelf);
	}
	
	public void delete(Shelf shelf) {
		shelfRepository.delete(shelf);
	}
	
	public void deleteById(Long id) {
		shelfRepository.deleteById(id);
	}
	
	public Optional<Shelf> findByName(String name) {
		return shelfRepository.findByName(name);
	}


}
