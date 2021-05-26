package com.timucin.gamelookup.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.timucin.gamelookup.domainutils.GameDeserializer;

@Entity
@Table(name = "game")
@JsonDeserialize(using = GameDeserializer.class)
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "game_genre",
		joinColumns = @JoinColumn(name = "game_id"),
		inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;
	
	@ElementCollection
	private Set<String> platforms;
	
	private LocalDate releaseDate;
	
	private Integer metacriticScore;
	
	private String esrbRating;
	
	private String coverImageUrl;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "game_collections",
		joinColumns = @JoinColumn(name = "game_id"),
		inverseJoinColumns = @JoinColumn(name = "game_collection_id"))
	private List<Shelf> gameCollection;

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

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(Set<String> platforms) {
		this.platforms = platforms;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getMetacriticScore() {
		return metacriticScore;
	}

	public void setMetacriticScore(Integer metacriticScore) {
		this.metacriticScore = metacriticScore;
	}

	public String getEsrbRating() {
		return esrbRating;
	}

	public void setEsrbRating(String esrbRating) {
		this.esrbRating = esrbRating;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public List<Shelf> getGameCollection() {
		return gameCollection;
	}

	public void setGameCollection(List<Shelf> gameCollection) {
		this.gameCollection = gameCollection;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", genres=" + genres + ", platforms=" + platforms
				+ ", releaseDate=" + releaseDate + ", metacriticScore=" + metacriticScore + ", esrbRating=" + esrbRating
				+ "]";
	}

	
}
