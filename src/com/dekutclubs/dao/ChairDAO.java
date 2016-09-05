package com.dekutclubs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dekutclubs.model.ChairModel;
import com.dekutclubs.model.ChairModel;

@Repository
@Transactional
public class ChairDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ChairModel getById(int adminid)
	{
		return (ChairModel) sessionFactory.getCurrentSession().get(ChairModel.class, adminid);
	}
	@SuppressWarnings("unchecked")
	public List<ChairModel> searchChair(String first_name)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ChairModel.class);
		criteria.add(Restrictions.ilike("first_name", first_name+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ChairModel> getAllChairs()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ChairModel.class);
		return criteria.list();
	}
	
	public int save(ChairModel chair)
	{
		chair.setStatus("chair");
		return (Integer) sessionFactory.getCurrentSession().save(chair);
	}
	
	public void update(ChairModel chair)
	{
		chair.setStatus("chair");
		sessionFactory.getCurrentSession().merge(chair);
		
	}
	
	public void delete(int chairId)
	{
		ChairModel c = getById(chairId);
		sessionFactory.getCurrentSession().delete(c);
	}
}
