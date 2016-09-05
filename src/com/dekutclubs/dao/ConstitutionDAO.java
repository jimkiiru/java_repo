package com.dekutclubs.dao;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dekutclubs.model.*;

@Repository
@Transactional
public class ConstitutionDAO
{
	
	int yr, mon, day;
	String currdate = "";
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public ConstitutionModel getById(int fieldid)
	{
		return (ConstitutionModel) sessionFactory.getCurrentSession().get(ConstitutionModel.class, fieldid);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstitutionModel> searchConstitutions(String clubname)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConstitutionModel.class);
		criteria.add(Restrictions.ilike("clubname", clubname+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstitutionModel> getAllConstitutions()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConstitutionModel.class);
		criteria.addOrder(Order.desc("currentDate"));
		return criteria.list();
	}
	
	public int save(ConstitutionModel constitution)
	{
		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		mon = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		currdate = yr + "-" + mon + "-" + day;
		
		constitution.setCurrentDate(currdate);
		return (Integer) sessionFactory.getCurrentSession().save(constitution);
	}
	
	public void update(ConstitutionModel constitution)
	{
				
		sessionFactory.getCurrentSession().merge(constitution);
	}
	
	public void delete(int fieldid)
	{
		ConstitutionModel c = getById(fieldid);
		sessionFactory.getCurrentSession().delete(c);
	}
	
	
}
