package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.ServletContext;
import javax.validation.constraints.Size;

@Entity 
@Table(name="administrators")
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AdminId", nullable = false)
	private int adminid;
	@Column(name = "FirstName", nullable = true)
	 private String first_name;
	@Column(name = "LastName", nullable = true)
	 private String lname;
	@Column(name = "EmailAddress", nullable = false,unique=true)
	 private String emailaddress;
	@Size(min=10,max=10,message="Please enter a 10 digit phone number")
	@Column(name = "CellPhone", nullable = false,unique=true)
	 private String cellphone;
	@Column(name = "Password", nullable = false)
	 private String password;
	@Column(name = "Status", nullable = false)
	 private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 

}

