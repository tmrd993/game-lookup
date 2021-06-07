package com.timucin.gamelookup.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.timucin.gamelookup.domain.Game;
import com.timucin.gamelookup.domain.Genre;
import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.dto.ChosenWebSearchResultDto;
import com.timucin.gamelookup.fetcher.GameDataFetcher;
import com.timucin.gamelookup.service.GameService;
import com.timucin.gamelookup.service.GenreService;
import com.timucin.gamelookup.service.ShelfService;
import com.timucin.gamelookup.service.UserService;

@Controller
@SessionAttributes("searchResults")
public class WebSearchController {
	
	Logger logger = LoggerFactory.getLogger(WebSearchController.class);
	
	private final GameDataFetcher gameDataFetcher;
	private final UserService userService;
	private final ShelfService shelfService;
	private final GameService gameService;
	private final GenreService genreService;

	@Autowired
	public WebSearchController(GameDataFetcher gameDataFetcher, UserService userService, ShelfService shelfService, GameService gameService, GenreService genreService) {
		this.gameDataFetcher = gameDataFetcher;
		this.userService = userService;
		this.shelfService = shelfService;
		this.gameService = gameService;
		this.genreService = genreService;
	}
	
	@GetMapping("/web_search")
	public String results(@AuthenticationPrincipal User user, 
			@RequestParam(required = false, value = "query") String query,
			Model model) {
		
		if(query != null) {
			logger.info("Search query: " + query);

			List<Game> searchResults = gameDataFetcher.fetchAll(query);

			logger.info("Found " + searchResults.size() + " results.");

			model.addAttribute("searchResults", searchResults);	
		}
		
		User currentUser = userService.findById(user.getId()).get();
		
		model.addAttribute("userShelves", currentUser.getShelves());
		model.addAttribute("chosenWebSearchResultDto", new ChosenWebSearchResultDto());
		
		return "web_search";
	}
	
	@PostMapping("/web_search")
	public String addGameToShelf(@AuthenticationPrincipal User user,
			@ModelAttribute("chosenWebSearchResultDto") @Valid ChosenWebSearchResultDto chosenWebSearchResultDto,
			BindingResult bindingResult,
			@ModelAttribute("searchResults") List<Game> searchResults,
			SessionStatus sessionStatus,
			RedirectAttributes attr,
			Model model) {
		
		addToShelf(searchResults, chosenWebSearchResultDto);
		
		sessionStatus.setComplete();
		
		attr.addFlashAttribute("searchResults", searchResults);
		
		return "redirect:/web_search";
	}
	
	private void addToShelf(List<Game> searchResults, ChosenWebSearchResultDto chosenWebSearchResultDto) {
		Game chosenGame = searchResults.get(chosenWebSearchResultDto.getChosenGameIndex() - 1);
		Shelf chosenShelf = shelfService.findById(chosenWebSearchResultDto.getChosenShelfId()).get();
		
		Optional<Game> potentialDuplicateGame = gameService.findByName(chosenGame.getName());
		
		if(chosenShelf.getGames().contains(chosenGame)) {
			return;
		}
		
		if(potentialDuplicateGame.isPresent()) {
			chosenShelf.getGames().add(potentialDuplicateGame.get());
		} else {
			
			for(Genre genre : chosenGame.getGenres()) {
				Optional<Genre> potentialDuplicateGenre = genreService.findByName(genre.getName());
				if(potentialDuplicateGenre.isPresent()) {
					genre.setId(potentialDuplicateGenre.get().getId());
				}
				genreService.save(genre);
			}
			
			gameService.save(chosenGame);
			chosenShelf.getGames().add(chosenGame);
		}
		
		shelfService.save(chosenShelf);
	}
	


}
