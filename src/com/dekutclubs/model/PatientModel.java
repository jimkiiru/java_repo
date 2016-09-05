package com.dekutclubs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="Patient")
public class PatientModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name= "PatientId", nullable =false)
	private int patientid;
    @Column(name= "FirstName", nullable =false)
    private String firstname; 
    @Column(name= "MiddleName", nullable =false)
    private String middlename; 
    @Column(name= "SurName", nullable =false)
    private String surname;
    @Column(name= "MaidenName", nullable =false)
    private String maidenname; 
    @Column(name= "IdNumber", nullable =false)
    private int idnumber; 
    @Column(name= "Pno", nullable =false)
    private String pno;
    @Column(name= "PhoneNOK", nullable =false)
    private int phone_nok;
    @Column(name= "MaritalStatus", nullable =false)
    private String marital_status;
    @Column(name= "DOB", nullable =false)
    private String dob;
    @Column(name= "Gender", nullable =false)
    private String gender;
    @Column(name= "BirthPlace", nullable =false)
    private String birthplace;
    @Column(name= "Tribe", nullable =false)
    private String tribe;
    @Column(name= "Religion", nullable =false)
    private String religion;
    @Column(name= "EducationLevel", nullable =false)
    private String educationlevel;
    @Column(name= "Occupation", nullable =false)
    private String occupation;
    @Column(name= "SmokingStatus", nullable =false)
    private String smoking_status;
    @Column(name= "DrinkingStatus", nullable =false)
    private String drinking_status;
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMaidenname() {
		return maidenname;
	}
	public void setMaidenname(String maidenname) {
		this.maidenname = maidenname;
	}
	public int getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(int idnumber) {
		this.idnumber = idnumber;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public int getPhone_nok() {
		return phone_nok;
	}
	public void setPhone_nok(int phone_nok) {
		this.phone_nok = phone_nok;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getTribe() {
		return tribe;
	}
	public void setTribe(String tribe) {
		this.tribe = tribe;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getEducationlevel() {
		return educationlevel;
	}
	public void setEducationlevel(String educationlevel) {
		this.educationlevel = educationlevel;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getSmoking_status() {
		return smoking_status;
	}
	public void setSmoking_status(String smoking_status) {
		this.smoking_status = smoking_status;
	}
	public String getDrinking_status() {
		return drinking_status;
	}
	public void setDrinking_status(String drinking_status) {
		this.drinking_status = drinking_status;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
     
}
