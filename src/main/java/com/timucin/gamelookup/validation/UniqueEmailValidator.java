package com.timucin.gamelookup.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.timucin.gamelookup.domain.User;
import com.timucin.gamelookup.service.UserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private final UserService userService;
	
	@Autowired
	public UniqueEmailValidator(UserService userService) {
		this.userService = userService;	
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = userService.findByEmail(value);
		
		if(user == null) {
			return true;
		}
		
		return false;
	}


}
