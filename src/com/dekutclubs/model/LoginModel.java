package com.dekutclubs.model;

import javax.persistence.*;
@Entity
@Table(name = "login")
public class LoginModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LoginId", nullable = false)
	private int logid;
	@Column(name = "ChairId", nullable = false)
	private int cid;
	@Column(name = "Username", nullable = false)
	 private String username;
	@Column(name = "Password", nullable = false)
	 private String password;
	@Column(name = "Status", nullable = false)
	 private String status;
	@Column(name = "Verdict", nullable = true)
	 private String verdict;
	
	public int getLogid() {
		return logid;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVerdict() {
		return verdict;
	}
	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}
	

}
