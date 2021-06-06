package com.timucin.gamelookup.dto;

public class ChosenWebSearchResultDto {
	
	private String chosenGameName;
	
	private long chosenShelfId;
	
	public ChosenWebSearchResultDto(String chosenGameName, long chosenShelfId) {
		this.chosenGameName = chosenGameName;
		this.chosenShelfId = chosenShelfId;
	}
	
	public ChosenWebSearchResultDto() {}
	
	public String getChosenGameName() {
		return chosenGameName;
	}
	public void setChosenGameName(String chosenGameName) {
		this.chosenGameName = chosenGameName;
	}

	public long getChosenShelfId() {
		return chosenShelfId;
	}

	public void setChosenShelfId(long chosenShelfId) {
		this.chosenShelfId = chosenShelfId;
	}
	
	
	

}
