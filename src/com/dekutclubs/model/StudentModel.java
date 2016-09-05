package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
@Table(name="Student")
public class StudentModel {

	@Id
	@Column(name = "RegNum", nullable = false,unique=true)
	 private String regNo;
	@Column(name = "Middlename", nullable = false)
	 private String fName;
	@Column(name = "LastName", nullable = false)
	 private String lName;
	@Size(min=10,max=10,message="Please enter a 10 digit phone number")
	@Column(name = "SurName", nullable = false)
	 private String surName;
	
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
}
