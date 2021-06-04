package com.timucin.gamelookup.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.timucin.gamelookup.validation.PasswordsMatch;
import com.timucin.gamelookup.validation.UniqueEmail;
import com.timucin.gamelookup.validation.UniqueUser;

@PasswordsMatch
public class UserDto {
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]+",
	  message = "only alphanumeric characters are allowed (a-z, A-Z, 0-9). please choose a different username")
	@UniqueUser
	private String username;
	
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 30)
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
