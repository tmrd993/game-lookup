package com.timucin.gamelookup.fetcher;

import java.util.List;

import com.timucin.gamelookup.domain.Game;

public interface GameDataFetcher {
	
	List<Game> fetchAll(String query);

}
