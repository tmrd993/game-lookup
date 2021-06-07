package com.timucin.gamelookup.dto;

public class ChosenWebSearchResultDto {
	
	private int chosenGameIndex;
	
	private long chosenShelfId;
	
	public ChosenWebSearchResultDto(int chosenGameIndex, long chosenShelfId) {
		this.chosenGameIndex = chosenGameIndex;
		this.chosenShelfId = chosenShelfId;
	}
	
	public ChosenWebSearchResultDto() {}
	
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
