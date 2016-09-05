package com.dekutclubs.model;

import javax.validation.constraints.NotNull;

public class SelectionModel {

	@NotNull
	private String clubName;
	private String year;
	
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
