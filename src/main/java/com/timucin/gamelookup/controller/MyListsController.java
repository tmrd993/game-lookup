package com.timucin.gamelookup.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.timucin.gamelookup.domain.Shelf;
import com.timucin.gamelookup.domain.User;
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
		
		return "my_lists";
	}

}
