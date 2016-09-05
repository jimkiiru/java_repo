package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="ChairPersons")
public class ChairModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ChairId", nullable = false)
	private int chairid;
	@Column(name = "FirstName", nullable = false)
	 private String first_name;
	@Column(name = "LastName", nullable = false)
	 private String lname;
	@Column(name = "SurName", nullable = false)
	 private String sname;
	@Column(name = "RegNumber", nullable = false,unique=true)
	 private String regnum;
	@Column(name = "EmailAddress", nullable = false,unique=true)
	 private String emailaddress;
	@Size(min=10,max=10,message="Please enter a 10 digit phone number") 
	@Column(name = "CellPhone", nullable = false,unique=true)
	 private String cellphone;
	@Column(name = "Password", nullable = false)
	 private String password;
	@Column(name = "Status", nullable = false)
	 private String status;
	@Column(name = "Verdict", nullable = true)
	 private String verdict;
	
	public String getVerdict() {
		return verdict;
	}
	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getChairid() {
		return chairid;
	}
	public void setChairid(int chairid) {
		this.chairid = chairid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
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
	public String getRegnum() {
		return regnum;
	}
	public void setRegnum(String regnum) {
		this.regnum = regnum;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

