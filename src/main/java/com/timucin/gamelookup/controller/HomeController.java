package com.timucin.gamelookup.controller;

import javax.validation.Valid;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.timucin.gamelookup.dto.ContactFormDto;
import com.timucin.gamelookup.service.EmailService;

@Controller
public class HomeController {
	
	private final EmailService emailService;
	
	public HomeController(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping({ "", "/", "home", "home.html" })
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model) {
		if(isAuthenticated()) {
			return "redirect:home";
		}
		return "login";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("contactFormDto", new ContactFormDto());
		return "contact";
	}
	
	@PostMapping("/contact")
	public String sendContactData(Model model,
			@ModelAttribute("contactFormDto") @Valid ContactFormDto contactFormDto,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "contact";
		}
		
		String name = contactFormDto.getName();
		String email = contactFormDto.getEmail();
		String message = contactFormDto.getDescription();
		
		emailService.sendSimpleMessage(name, email, message);
		
		return "contact_success";
	}

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}

}
