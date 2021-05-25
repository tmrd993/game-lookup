package com.timucin.gamelookup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.GameCollection;
import com.timucin.gamelookup.repository.GameCollectionRepository;

@Service
public class GameCollectionService {
	
	private final GameCollectionRepository gameCollectionRepository;
	
	@Autowired
	public GameCollectionService(GameCollectionRepository gameCollectionRepository) {
		this.gameCollectionRepository = gameCollectionRepository;
	}
	
	public List<GameCollection> findAll() {
		return gameCollectionRepository.findAll();
	}

}
