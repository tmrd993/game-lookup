package com.timucin.gamelookup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSearchController {
	
	
	@GetMapping("/web_search")
	public String webSearch() {
		return "web_search";
	}

}
