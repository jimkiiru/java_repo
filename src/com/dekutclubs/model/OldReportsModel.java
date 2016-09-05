package com.dekutclubs.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="oldfiles")
public class OldReportsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Count", nullable = false)
	private int count;
	@Column(name = "ClubId", nullable = false)
	private String clubid;
	
	@Column(name = "UploadedFile", nullable = false)
	private String url;
	@Column(name = "UploadDate", nullable = true)
	private String uploaddate;
	@Column(name = "CurrentDate", nullable = true)
	private String currentdate;
	
	
	
	
	public String getClubid() {
		return clubid;
	}
	public void setClubid(String clubid) {
		this.clubid = clubid;
	}
	public String getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
