package com.timucin.gamelookup.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.timucin.gamelookup.domain.Game;
import com.timucin.gamelookup.fetcher.GameDataFetcher;

@Controller
public class WebSearchController {
	
	Logger logger = LoggerFactory.getLogger(WebSearchController.class);
	
	private final GameDataFetcher gameDataFetcher;

	@Autowired
	public WebSearchController(GameDataFetcher gameDataFetcher) {
		this.gameDataFetcher = gameDataFetcher;
	}
	
	@GetMapping("web_search/search")
	public String results(Model model, @RequestParam(required = true, value = "query") String query) {
		
		logger.info("Search query: " + query);

		List<Game> searchResults = gameDataFetcher.fetchAll(query);

		logger.info("Found " + searchResults.size() + " results.");

		model.addAttribute("searchResults", searchResults);
		
		return "search_results";
	}

}
