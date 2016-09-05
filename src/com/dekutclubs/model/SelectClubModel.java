package com.dekutclubs.model;

import javax.validation.constraints.NotNull;

public class SelectClubModel {

	@NotNull
	private String clubName;
	private String year;
	private String position;
	
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
