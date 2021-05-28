package com.timucin.gamelookup.domain;

import javax.persistence.Entity;

@Entity
public class User {
	
	private String username;
	
	// TODO: this should be a hash, not plaintext
	private String password;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//TODO: maybe add roles later?

}
