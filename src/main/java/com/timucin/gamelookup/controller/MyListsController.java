package com.timucin.gamelookup.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.dto.ShelfDto;
import com.timucin.gamelookup.service.UserService;

@Controller
public class MyListsController {
	
	Logger logger = LoggerFactory.getLogger(MyListsController.class);
	
	private final UserService userService;
	
	public MyListsController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/my_lists")
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
	
	@PostMapping("/my_lists")
	public String createList(@AuthenticationPrincipal User user,
			@ModelAttribute("shelfDto") @Valid ShelfDto shelfDto,
			BindingResult bindingResult,
			RedirectAttributes attr,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.shelfDto", bindingResult);
			attr.addFlashAttribute("shelfDto", shelfDto);
			return "redirect:/my_lists#new-list-popup";
		}
		
		User currentUser = userService.findById(user.getId()).get();
		Shelf shelf = new Shelf();
		shelf.setName(shelfDto.getName());
		shelf.setDescription(shelfDto.getDescription());
		shelf.setShelfOwner(currentUser);
		
		currentUser.getShelves().add(shelf);
		userService.save(currentUser);
		
		return "redirect:my_lists";
	}

}
