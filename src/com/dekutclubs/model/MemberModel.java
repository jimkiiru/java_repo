package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity 
@Table(name="Members")
public class MemberModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MemberId", nullable = false)
	private int memberid;	
	@Column(name = "RegNo", nullable = false,unique=true)
	private String regno;	
	@Column(name = "FirstName", nullable = true)
	private String fname;
	@Column(name = "LastName", nullable = true)
	private String lname;
	@Column(name = "Surname", nullable = true)
	private String sname;
	@Column(name = "Position", nullable = false)
	private String position;
	@Column(name = "EmailAddress", nullable = true)
	private String emailaddress;
	@Size(min=10,max=10,message="Please Enter a 10 Digit Phone Number")
	@Column(name = "CellPhone", nullable = false,unique=true)
	private String cellphone;
	@Column(name = "ClubName", nullable = false)
	private String clubName;
	@Column(name = "YearOfRegistration", nullable = false)
	private String yearOfRegistration;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getYearOfRegistration() {
		return yearOfRegistration;
	}
	public void setYearOfRegistration(String yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}
	

}