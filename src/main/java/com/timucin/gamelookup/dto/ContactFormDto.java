package com.timucin.gamelookup.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactFormDto {
	
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 100)
	private String name;
	
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 300)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
