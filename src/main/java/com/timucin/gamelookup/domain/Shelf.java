package com.timucin.gamelookup.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.util.UriUtils;

@Entity
@Table(name = "shelf")
public class Shelf {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description = "";
	
	@Transient
	private String encodedName;
	
	@ManyToMany
	@JoinTable(name = "shelf_games",
		joinColumns = @JoinColumn(name = "shelf_id"),
		inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User shelfOwner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public User getShelfOwner() {
		return shelfOwner;
	}
	
	public void setShelfOwner(User owner) {
		shelfOwner = owner;
	}
	
	public String getEncodedName() {
		return UriUtils.encodePathSegment(name, "UTF-8");
	}

	@Override
	public String toString() {
		return "GameCollection [id=" + id + ", name=" + name + ", games=" + games + "]";
	}
	
	

}
