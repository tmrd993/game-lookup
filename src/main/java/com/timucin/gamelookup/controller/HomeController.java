package com.timucin.gamelookup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"", "/", "home", "home.html"})
	public String home() {
		return "home";
	}

}
