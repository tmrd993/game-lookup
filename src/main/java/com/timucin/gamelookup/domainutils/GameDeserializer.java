package com.timucin.gamelookup.domainutils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.timucin.gamelookup.domain.Game;
import com.timucin.gamelookup.domain.Genre;

public class GameDeserializer extends JsonDeserializer<Game> {

	@Override
	public Game deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Game game = new Game();
		JsonNode root = p.readValueAsTree();
		String name = root.get("name").asText();
		
		LocalDate releaseDate = LocalDate.MAX;
		if(!root.get("released").isNull()) {
			String releaseDateStr = root.get("released").asText();
			releaseDate = LocalDate.of(Integer.parseInt(releaseDateStr.substring(0, 4)),
					Integer.parseInt(releaseDateStr.substring(5, 7)),
					Integer.parseInt(releaseDateStr.substring(8, releaseDateStr.length())));
		}
		
		int metacriticScore = -1;
		if(!root.get("metacritic").isNull()) {
			metacriticScore = root.get("metacritic").asInt();
		}
		
		String esrbRating = "Not available";
		if(!root.get("esrb_rating").isNull()) {
			esrbRating = root.get("esrb_rating").get("name").asText();
		}
		
		String backgroundImageUrl = "";
		if(!root.get("background_image").isNull()) {
			backgroundImageUrl = root.get("background_image").asText();
		}
		
		Set<Genre> genres = new HashSet<>();
		if(!root.get("genres").isNull()) {
			ArrayNode genreNode = (ArrayNode) root.get("genres");
			for(JsonNode genreField : genreNode) {
				Genre genre = new Genre();
				genre.setName(genreField.get("name").asText());
				genres.add(genre);
			}
		}
		
		Set<String> platforms = new HashSet<>();
		if(!root.get("platforms").isNull()) {
			ArrayNode platformNode = (ArrayNode) root.get("platforms");
			for(JsonNode platformField : platformNode) {
				platforms.add(platformField.get("platform").get("name").asText());
			}
		}
		
		game.setName(name);
		game.setGenres(genres);
		game.setEsrbRating(esrbRating);
		game.setMetacriticScore(metacriticScore);
		game.setPlatforms(platforms);
		game.setReleaseDate(releaseDate);
		game.setCoverImageUrl(backgroundImageUrl);
		
		return game;
	}


}
