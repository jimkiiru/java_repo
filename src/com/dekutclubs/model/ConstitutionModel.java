package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="constitution")
public class ConstitutionModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fieldid;
	@Column(name = "ClubName", nullable = false)
	private String clubname;
	@Column(name = "UploadedConstitution", nullable = false)
	private String url;
	@Column(name = "ChairId", nullable = false)
	private int chairid;
	@Column(name = "CurrentDate", nullable = false)
	private String currentDate;
	
	
	public int getChairid() {
		return chairid;
	}
	public void setChairid(int chairid) {
		this.chairid = chairid;
	}
	public String getClubname() {
		return clubname;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	public int getFieldid() {
		return fieldid;
	}
	public void setFieldid(int fieldid) {
		this.fieldid = fieldid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	
}
