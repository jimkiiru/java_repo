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
public class EventDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public EventModel getById(int eventid)
	{
		return (EventModel) sessionFactory.getCurrentSession().get(EventModel.class, eventid);
	}
	
	@SuppressWarnings("unchecked")
	public List<EventModel> searchEvents(String eventName)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EventModel.class);
		criteria.add(Restrictions.ilike("eventname", eventName+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<EventModel> getAllEvents()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EventModel.class);
		return criteria.list();
	}
	
	public int save(EventModel event)
	{		
		return (Integer) sessionFactory.getCurrentSession().save(event);
	}
	
	public void update(EventModel event)
	{
		sessionFactory.getCurrentSession().merge(event);
	}
	
	public void delete(int eventid)
	{
		EventModel c = getById(eventid);
		sessionFactory.getCurrentSession().delete(c);
	}
}
