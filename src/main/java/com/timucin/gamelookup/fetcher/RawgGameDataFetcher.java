package com.timucin.gamelookup.fetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.Game;

/**
 * 
 * fetches data from Rawg (https://rawg.io/)
 */
@Service
@Primary
public class RawgGameDataFetcher implements GameDataFetcher {
	
	@Value("${game.db.rawg.url}")
	private String rawgRequestUrl;

	@Override
	public List<Game> fetchAll(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
