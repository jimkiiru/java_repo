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
public class CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ClubCategoryModel getById(int categoryid)
	{
		return (ClubCategoryModel) sessionFactory.getCurrentSession().get(ClubCategoryModel.class, categoryid);
	}
	@SuppressWarnings("unchecked")
	public List<ClubCategoryModel> searchCategories(String categoryname)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClubCategoryModel.class);
		criteria.add(Restrictions.ilike("categoryname", categoryname+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ClubCategoryModel> getAllCategories()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClubCategoryModel.class);
		return criteria.list();
	}
	
	public int save(ClubCategoryModel category)
	{
		
		return (Integer) sessionFactory.getCurrentSession().save(category);
	}
	
	public void update(ClubCategoryModel category)
	{
		sessionFactory.getCurrentSession().merge(category);
		
	}
	
	public void delete(int categoryid)
	{
		ClubCategoryModel m = getById(categoryid);
		sessionFactory.getCurrentSession().delete(m);
	}
}
