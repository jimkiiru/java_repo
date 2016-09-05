package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="ClubCategory")
public class ClubCategoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CategoryId", nullable = false)
	private int categoryid;
	@Column(name = "CategoryName", nullable = true)
	 private String categoryname;
	
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	
}
