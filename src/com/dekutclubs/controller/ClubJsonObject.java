package com.dekutclubs.controller;

import java.util.List;

import com.dekutclubs.model.*;

public class ClubJsonObject {
	  int iTotalRecords;

	    int iTotalDisplayRecords;

	    String sEcho;

	    String sColumns;

	    List<ConstitutionModel> aaData;
	   
	    public int getiTotalRecords() {
		return iTotalRecords;
	    }

	    public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	    }

	    public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	    }

	    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	    }

	    public String getsEcho() {
		return sEcho;
	    }

	    public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	    }

	    public String getsColumns() {
		return sColumns;
	    }

	    public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	    }

		public List<ConstitutionModel> getAaData() {
			return aaData;
		}

		public void setAaData(List<ConstitutionModel> aaData) {
			this.aaData = aaData;
		}

	   
}
