package com.dekutclubs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Clubregister")
public class ClubModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ClubId", nullable = false)
	private int clubid;
	@Column(name = "ChairId", nullable = false)
	private int chairid;
	@Column(name = "ClubName", nullable = false,unique=true)
	private String clubname;
	@Column(name = "ClubPatron", nullable = false)
	private String clubpatron;
	@Column(name = "patronEmail", nullable = false)
	private String patronEmail;
	@Size(min=10,max=10,message="Please Enter a 10 Digit Phone Number")
	@Column(name = "PatronContact", nullable = false)
	private String patronContact;	
	@Column(name = "Chairperson", nullable = false)
	private String chairperson;
	@Column(name = "FormationDate", nullable = false)
	private String formationdate;
	@Column(name = "MeetingVenue", nullable = false)
	private String meetingvenue;
	@Column(name = "ChairpersonEmail", nullable = false)
	private String chairpersonemail;
	@Column(name = "ChairpersonContact", nullable = false)
	private int chairpersoncontact;
	@Column(name = "Category", nullable = false)
	private String category;
	private String status;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
	public int getChairid() {
		return chairid;
	}
	public void setChairid(int chairid) {
		this.chairid = chairid;
	}
	public String getPatronEmail() {
		return patronEmail;
	}
	public void setPatronEmail(String patronEmail) {
		this.patronEmail = patronEmail;
	}
	public String getPatronContact() {
		return patronContact;
	}
	public void setPatronContact(String patronContact) {
		this.patronContact = patronContact;
	}
	public int getClubid() {
		return clubid;
	}
	public void setClubid(int clubid) {
		this.clubid = clubid;
	}

	public String getClubname() {
		return clubname;
	}

	public void setClubname(String clubname) {
		this.clubname = clubname;
	}

	public String getClubpatron() {
		return clubpatron;
	}

	public void setClubpatron(String clubpatron) {
		this.clubpatron = clubpatron;
	}

	public String getChairperson() {
		return chairperson;
	}

	public void setChairperson(String chairperson) {
		this.chairperson = chairperson;
	}

	public String getFormationdate() {
		return formationdate;
	}

	public void setFormationdate(String formationdate) {
		this.formationdate = formationdate;
	}

	public String getMeetingvenue() {
		return meetingvenue;
	}

	public void setMeetingvenue(String meetingvenue) {
		this.meetingvenue = meetingvenue;
	}

	public String getChairpersonemail() {
		return chairpersonemail;
	}

	public void setChairpersonemail(String chairpersonemail) {
		this.chairpersonemail = chairpersonemail;
	}

	public int getChairpersoncontact() {
		return chairpersoncontact;
	}

	public void setChairpersoncontact(int chairpersoncontact) {
		this.chairpersoncontact = chairpersoncontact;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}