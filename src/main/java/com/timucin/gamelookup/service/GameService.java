package com.timucin.gamelookup.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.Game;
import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.repository.GameRepository;

@Service
public class GameService {
	
	private final GameRepository gameRepository;
	
	@Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	public List<Game> findAll() {
		return gameRepository.findAll();
	}
	
	public Optional<Game> findById(Long id) {
		return gameRepository.findById(id);
	}
	
	public Game save(Game game) {
		return gameRepository.save(game);
	}
	
	public List<Game> saveAll(Iterable<Game> games) {
		return gameRepository.saveAll(games);
	}
	
	public void delete(Game game) {
		gameRepository.delete(game);
	}
	
	public void deleteById(Long id) {
		gameRepository.deleteById(id);
	}
	
	public Optional<Game> findByName(String name) {
		return gameRepository.findByName(name);
	}
	
	public Page<Game> findAll(Pageable pageable){
		return gameRepository.findAll(pageable);
	}
	
	public Page<Game> findPaginatedFromShelf(Shelf shelf, Pageable pageable){
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItemIndex = pageSize * currentPage;
		
		List<Game> games = shelf.getGames();
		List<Game> gamesTemp;
		if(games.size() < startItemIndex) {
			gamesTemp = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItemIndex + pageSize, games.size());
			gamesTemp = games.subList(startItemIndex, toIndex);
		}
		
		Page<Game> gamePage = new PageImpl<>(gamesTemp, PageRequest.of(currentPage, pageSize), games.size());
		
		return gamePage;
	}

}
