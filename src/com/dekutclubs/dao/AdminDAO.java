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
public class AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public AdminModel getById(int adminid)
	{
		return (AdminModel) sessionFactory.getCurrentSession().get(AdminModel.class, adminid);
	}
	@SuppressWarnings("unchecked")
	public List<AdminModel> searchAdmin(String first_name)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdminModel.class);
		criteria.add(Restrictions.ilike("first_name", first_name+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdminModel> getAllAdmins()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AdminModel.class);
		return criteria.list();
	}
	
	public int save(AdminModel admin)
	{
		int i;
		admin.setStatus("admin");
		i=(Integer) sessionFactory.getCurrentSession().save(admin);
		return i;
	}
	
	public void update(AdminModel admin)
	{
		admin.setStatus("admin");
		sessionFactory.getCurrentSession().merge(admin);
		
	}
	
	public void delete(int adminid)
	{
		AdminModel a = getById(adminid);
		sessionFactory.getCurrentSession().delete(a);
	}
}
