package com.timucin.gamelookup.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.timucin.gamelookup.dto.UserDto;
import com.timucin.gamelookup.service.UserService;

@Controller
public class SignUpController {
	
	Logger logger = LoggerFactory.getLogger(SignUpController.class);
	private final UserService userService;
	
	@Autowired
	public SignUpController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/sign_up")
	public String showSignUpForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "sign_up";
	}
	
	@PostMapping("/sign_up")
	public ModelAndView registerUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			for(ObjectError error : bindingResult.getAllErrors()) {
				logger.info(error.getDefaultMessage());
			}
			return new ModelAndView("sign_up");
		}
		
		userService.registerNewUserAccount(userDto);
		model.addAttribute("user", userDto);
		
		ModelAndView redirectView = new ModelAndView("register_success");
		redirectView.addObject("user", userDto);
		return redirectView;
	}
	
	

}
