package com.dekutclubs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dekutclubs.model.*;

@Repository
@Transactional
public class StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public StudentModel getById(int regNo)
	{
		return (StudentModel) sessionFactory.getCurrentSession().get(StudentModel.class, regNo);
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentModel> getAllStudents()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentModel.class);
		return criteria.list();
	}
	
	public int save(StudentModel student)
	{
		int i;		
		i=(Integer) sessionFactory.getCurrentSession().save(student);
		return i;
	}
}
