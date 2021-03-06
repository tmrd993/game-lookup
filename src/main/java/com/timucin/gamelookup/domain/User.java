package com.timucin.gamelookup.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private String email;
	
	@OneToMany(mappedBy = "shelfOwner", cascade = CascadeType.MERGE)
	private List<Shelf> shelves = new ArrayList<>();
	
	public User(Long id, String username, String password, String email, List<Shelf> shelves) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.shelves = shelves;
	}
	
	public User() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

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
	
	public List<Shelf> getShelves() {
		return shelves;
	}
	
	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}
	
	@Override
	public String toString() {
		return username;
	}
	
	//TODO: maybe add roles later?
	
}
