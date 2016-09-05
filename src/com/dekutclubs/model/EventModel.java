package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Events")
public class EventModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EventId", nullable = false)
	private int eventid;
	@Column(name = "EventName", nullable = false)
	private String eventName;
	@Column(name = "PlaceOfEvent", nullable = false)
	private String eventPlace;
	/*@Column(name = "Pics", nullable = false)
	private String pics;*/
	@Column(name = "Reports", nullable = false)
	private String url;
	@Column(name = "ClubName", nullable = false)
	private String clubname;
	
	
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventPlace() {
		return eventPlace;
	}
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClubname() {
		return clubname;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	
	
}
