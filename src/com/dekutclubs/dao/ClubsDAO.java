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
public class ClubsDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public ClubModel getById(int clubid)
	{
		return (ClubModel) sessionFactory.getCurrentSession().get(ClubModel.class, clubid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ClubModel> searchClubs(String clubname)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClubModel.class);
		criteria.add(Restrictions.ilike("clubname", clubname+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ClubModel> getAllClubs()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClubModel.class);
		return criteria.list();
	}
	
	public int save(ClubModel club)
	{
		club.setStatus("Active");
		return (Integer) sessionFactory.getCurrentSession().save(club);
	}
	
	public void update(ClubModel club)
	{
		club.setStatus("Active");
		sessionFactory.getCurrentSession().merge(club);
	}
	
	public void delete(int clubid)
	{
		ClubModel c = getById(clubid);
		sessionFactory.getCurrentSession().delete(c);
	}
}
