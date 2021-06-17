package com.timucin.gamelookup.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.timucin.gamelookup.domain.Game;
import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.dto.ChosenDeletionEntriesDto;
import com.timucin.gamelookup.dto.ShelfDto;
import com.timucin.gamelookup.service.GameService;
import com.timucin.gamelookup.service.ShelfService;
import com.timucin.gamelookup.service.UserService;

@Controller
public class MyListsController {
	
	Logger logger = LoggerFactory.getLogger(MyListsController.class);
	
	private final UserService userService;
	private final GameService gameService;
	private final ShelfService shelfService;
	
	@Autowired
	public MyListsController(UserService userService, GameService gameService, ShelfService shelfService) {
		this.userService = userService;
		this.gameService = gameService;
		this.shelfService = shelfService;
	}
	
	@GetMapping("/{username}/my_lists")
	public String myLists(@AuthenticationPrincipal User user, Model model) {
		
		// this is guaranteed to find the user since the endpoint can't be reached without being logged in
		User currentUser = userService.findById(user.getId()).get();
		
		List<Shelf> shelves = currentUser.getShelves();
		
		model.addAttribute("shelves", shelves);
		
		if(!model.containsAttribute("shelfDto")) {
			model.addAttribute("shelfDto", new ShelfDto());	
		} 
		
		return "my_lists";
	}
	
	@PostMapping("/{username}/my_lists")
	public String createList(@AuthenticationPrincipal User user,
			@PathVariable String username,
			@ModelAttribute("shelfDto") @Valid ShelfDto shelfDto,
			BindingResult bindingResult,
			RedirectAttributes attr,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.shelfDto", bindingResult);
			attr.addFlashAttribute("shelfDto", shelfDto);
			return "redirect:/" + username + "/my_lists#new-list-popup";
		}
		
		User currentUser = userService.findById(user.getId()).get();
		Shelf shelf = new Shelf();
		shelf.setName(shelfDto.getName());
		shelf.setDescription(shelfDto.getDescription());
		shelf.setShelfOwner(currentUser);
		
		currentUser.getShelves().add(shelf);
		userService.save(currentUser);
		
		return "redirect:/" + username + "/my_lists";
	}
	
	
	@GetMapping("/{username}/my_lists/{shelfId}")
	public String showDetailedList(@AuthenticationPrincipal User user,
			Model model,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size,
			@PathVariable String username,
			@PathVariable Long shelfId
			) {
		
		
		User currentUser = userService.findByUsername(user.getUsername());
		
		Optional<Shelf> possibleShelf = currentUser.getShelves()
				.stream()
				.filter(s -> s.getId().equals(shelfId))
				.findFirst();
		
		if(possibleShelf.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		
		Shelf targetShelf = possibleShelf.get();
		
		Page<Game> gamePage = gameService.findPaginatedFromShelf(targetShelf, PageRequest.of(page, size));
		
		model.addAttribute("gamePage", gamePage);
		
		int totalPages = gamePage.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		logger.info("pages: " + totalPages);
		
		ChosenDeletionEntriesDto chosenDeletionEntriesDto = new ChosenDeletionEntriesDto();
		for(int i = 0; i < gamePage.getNumberOfElements(); i++) {
			chosenDeletionEntriesDto.getChosenGames().add(new Game());
		}
		
		model.addAttribute("shelf", targetShelf);
		model.addAttribute("chosenDeletionEntriesDto", chosenDeletionEntriesDto);
		
		return "detailed_list";
	}
	
	
	@PostMapping(value = "/{username}/my_lists/{shelfId}", params = "delete")
	public String deleteSelectedEntries(@AuthenticationPrincipal User user,
			@ModelAttribute ChosenDeletionEntriesDto chosenDeletionEntriesDto,
			Model model,
			@PathVariable String username,
			@PathVariable Long shelfId
			) {
		
		User currentUser = userService.findByUsername(user.getUsername());
		
		Optional<Shelf> possibleShelf = currentUser.getShelves()
				.stream()
				.filter(s -> s.getId().equals(shelfId))
				.findFirst();
		
		if(possibleShelf.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		
		Shelf targetShelf = possibleShelf.get();
		
		Set<Long> chosenGameIds = chosenDeletionEntriesDto
				.getChosenGames()
				.stream()
				.map(s -> s.getId())
				.collect(Collectors.toSet());
		
		targetShelf.getGames().removeIf(game -> chosenGameIds.contains(game.getId()));
		
		for(Long id : chosenGameIds) {
			if(id != null) {
				if(!shelfService.containsGameAnyShelf(gameService.findById(id).get())) {
					gameService.deleteById(id);
				} 
			}
		}
		
		shelfService.save(targetShelf);
				
		return "redirect:/" + username + "/my_lists/" + shelfId;
	}

}
