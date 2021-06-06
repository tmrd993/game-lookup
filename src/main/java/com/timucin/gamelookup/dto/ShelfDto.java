package com.timucin.gamelookup.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ShelfDto {
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9 ]+",
			  message = "only alphanumeric characters and spaces are allowed (a-z, A-Z, 0-9). please choose a different name")
	private String name;

	@NotNull
	@Size(max = 200)
	@Pattern(regexp = "[a-zA-Z0-9 ]*",
			  message = "only alphanumeric characters and spaces are allowed (a-z, A-Z, 0-9). please choose a different description")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
