package com.timucin.gamelookup.domainutils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.timucin.gamelookup.domain.Game;

public class GameDeserializer extends JsonDeserializer<Game> {

	@Override
	public Game deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		
		
		return null;
	}


}
