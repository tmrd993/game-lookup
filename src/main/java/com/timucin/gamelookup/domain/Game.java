package com.timucin.gamelookup.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
	
	@ManyToMany
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
	
	@ManyToMany(mappedBy = "games")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coverImageUrl == null) ? 0 : coverImageUrl.hashCode());
		result = prime * result + ((esrbRating == null) ? 0 : esrbRating.hashCode());
		result = prime * result + ((metacriticScore == null) ? 0 : metacriticScore.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((platforms == null) ? 0 : platforms.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (coverImageUrl == null) {
			if (other.coverImageUrl != null)
				return false;
		} else if (!coverImageUrl.equals(other.coverImageUrl))
			return false;
		if (esrbRating == null) {
			if (other.esrbRating != null)
				return false;
		} else if (!esrbRating.equals(other.esrbRating))
			return false;
		if (metacriticScore == null) {
			if (other.metacriticScore != null)
				return false;
		} else if (!metacriticScore.equals(other.metacriticScore))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (platforms == null) {
			if (other.platforms != null)
				return false;
		} else if (!platforms.equals(other.platforms))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		return true;
	}
	
	
	

	

	

	
}
