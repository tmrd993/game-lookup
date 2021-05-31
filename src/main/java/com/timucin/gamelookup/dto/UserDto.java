package com.timucin.gamelookup.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.timucin.gamelookup.validation.PasswordsMatch;
import com.timucin.gamelookup.validation.UniqueEmail;
import com.timucin.gamelookup.validation.UniqueUser;

@PasswordsMatch
public class UserDto {
	
	@NotNull
	@NotEmpty
	@UniqueUser
	private String username;
	
	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;
	
	@NotNull
	@NotEmpty
	@Email
	@UniqueEmail
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
