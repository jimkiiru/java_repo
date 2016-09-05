package com.dekutclubs.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.dekutclubs.model.PatientModel;

@Repository
@Transactional
public class PatientDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int save(PatientModel patient)
	{
		
		return (Integer) sessionFactory.getCurrentSession().save(patient);
	}
	
	
}
