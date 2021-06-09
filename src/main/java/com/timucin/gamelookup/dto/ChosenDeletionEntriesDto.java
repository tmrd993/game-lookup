package com.timucin.gamelookup.dto;

import java.util.ArrayList;
import java.util.List;

import com.timucin.gamelookup.domain.Game;

public class ChosenDeletionEntriesDto {
	
    private List<Game> chosenGames;
    
    public ChosenDeletionEntriesDto() {
    	chosenGames = new ArrayList<>();
    }

	public List<Game> getChosenGames() {
		return chosenGames;
	}

	public void setChosenGames(List<Game> chosenGames) {
		this.chosenGames = chosenGames;
	}
    
    
}
