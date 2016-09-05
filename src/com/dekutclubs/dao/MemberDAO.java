package com.dekutclubs.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dekutclubs.model.MemberModel;

@Repository
@Transactional
public class MemberDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public MemberModel getById(int memberid)
	{
		return (MemberModel) sessionFactory.getCurrentSession().get(MemberModel.class, memberid);
	}
	@SuppressWarnings("unchecked")
	public List<MemberModel> searchMembers(String regno)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberModel.class);
		criteria.add(Restrictions.ilike("regno", regno+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberModel> getAllMembers()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberModel.class);
		return criteria.list();
	}
	
	public int save(MemberModel member)
	{
		
		return (Integer) sessionFactory.getCurrentSession().save(member);
	}
	
	public void update(MemberModel member)
	{
		sessionFactory.getCurrentSession().merge(member);
		
	}
	
	public void delete(int memberid)
	{
		MemberModel m = getById(memberid);
		sessionFactory.getCurrentSession().delete(m);
	}
}
