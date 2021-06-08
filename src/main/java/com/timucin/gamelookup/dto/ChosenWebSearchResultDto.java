package com.timucin.gamelookup.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import com.timucin.gamelookup.domain.Game;

public class ChosenWebSearchResultDto {
	
	private int chosenGameIndex;
	
	@Min(value = 1, message = "Please select a valid list")
	private long chosenShelfId;
	
	private List<Game> allResults;
	
	public ChosenWebSearchResultDto(int chosenGameIndex, long chosenShelfId, List<Game> allResults) {
		this.chosenGameIndex = chosenGameIndex;
		this.chosenShelfId = chosenShelfId;
		this.allResults = allResults;
	}
	
	public ChosenWebSearchResultDto() {
		allResults = new ArrayList<>();
	}
	
	public List<Game> getAllResults() {
		return allResults;
	}

	public void setAllResults(List<Game> allResults) {
		this.allResults = allResults;
	}
	
	public int getChosenGameIndex() {
		return chosenGameIndex;
	}
	public void setChosenGameIndex(int chosenGameIndex) {
		this.chosenGameIndex = chosenGameIndex;
	}

	public long getChosenShelfId() {
		return chosenShelfId;
	}

	public void setChosenShelfId(long chosenShelfId) {
		this.chosenShelfId = chosenShelfId;
	}
	
	
	

}
