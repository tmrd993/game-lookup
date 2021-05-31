package com.timucin.gamelookup.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.service.UserService;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

	private final UserService userService;
	
	@Autowired
	public UniqueUserValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = userService.findByUsername(value);
		
		if(user == null) {
			return true;
		}
		
		return false;
	}

}
