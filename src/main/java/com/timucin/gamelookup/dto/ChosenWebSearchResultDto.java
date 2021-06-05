package com.timucin.gamelookup.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.timucin.gamelookup.domain.Game;

public class ChosenWebSearchResultDto {
	
	private List<Game> allResults;
	
	@NotNull
	private Game chosenGame;
	
	public ChosenWebSearchResultDto(List<Game> allResults, Game chosenGame) {
		this.allResults = allResults;
		this.chosenGame = chosenGame;
	}
	
	public ChosenWebSearchResultDto() {}
	
	public List<Game> getAllResults() {
		return allResults;
	}
	public void setAllResults(List<Game> allResults) {
		this.allResults = allResults;
	}
	public Game getChosenGame() {
		return chosenGame;
	}
	public void setChosenGame(Game chosenGame) {
		this.chosenGame = chosenGame;
	}
	
	
	

}
