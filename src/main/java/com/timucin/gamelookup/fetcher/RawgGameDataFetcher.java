package com.timucin.gamelookup.fetcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import com.timucin.gamelookup.domain.Game;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * 
 * fetches data from Rawg (https://rawg.io/)
 */
@Service
@Primary
public class RawgGameDataFetcher implements GameDataFetcher {
	
	Logger logger = LoggerFactory.getLogger(RawgGameDataFetcher.class);
	
	@Value("${game.db.rawg.url}")
	private String rawgRequestUrl;

	@Override
	public List<Game> fetchAll(@NonNull String query) {
		
		OkHttpClient client = new OkHttpClient();
		
		String fullRequestUrl = rawgRequestUrl + UriUtils.encodeQuery(query, StandardCharsets.UTF_8);
		
		Request request = new Request.Builder()
				.url(fullRequestUrl)
				.build();
		
		List<Game> queryResults = new ArrayList<>();
		
		try {
			ResponseBody responseBody = client.newCall(request).execute().body();
			
			logger.info(responseBody.string());
			
			//TODO: implement json deserializer and convert response to list of games
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return queryResults;
	}

}
