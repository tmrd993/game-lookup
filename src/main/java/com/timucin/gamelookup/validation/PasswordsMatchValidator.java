package com.timucin.gamelookup.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.timucin.gamelookup.dto.UserDto;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserDto userDto = (UserDto) value;
		
		return userDto.getPassword().equals(userDto.getMatchingPassword());
	}

}
