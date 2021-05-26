package com.timucin.gamelookup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.repository.ShelfRepository;

@Service
public class ShelfService {
	
	private final ShelfRepository gameCollectionRepository;
	
	@Autowired
	public ShelfService(ShelfRepository gameCollectionRepository) {
		this.gameCollectionRepository = gameCollectionRepository;
	}
	
	public List<Shelf> findAll() {
		return gameCollectionRepository.findAll();
	}
	
	public Optional<Shelf> findById(Long id) {
		return gameCollectionRepository.findById(id);
	}
	
	public Shelf save(Shelf gameCollection) {
		return gameCollectionRepository.save(gameCollection);
	}
	
	public void delete(Shelf gameCollection) {
		gameCollectionRepository.delete(gameCollection);
	}
	
	public void deleteById(Long id) {
		gameCollectionRepository.deleteById(id);
	}
	
	public Optional<Shelf> findByName(String name) {
		return gameCollectionRepository.findByName(name);
	}


}
