package com.dekutclubs.dao;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dekutclubs.model.*;

@Repository
@Transactional
public class OldReportsDAO
{
	int yr, mon, day;
	String currdate = "";
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public OldReportsModel getById(int count)
	{
		return (OldReportsModel) sessionFactory.getCurrentSession().get(OldReportsModel.class, count);
	}
	
	@SuppressWarnings("unchecked")
	public List<OldReportsModel> searchReports(String clubid)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OldReportsModel.class);
		criteria.add(Restrictions.ilike("clubid", clubid+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OldReportsModel> getAllReports()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OldReportsModel.class);
		return criteria.list();
	}
	
	public int save(OldReportsModel orm)
	
	{
		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		mon = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		currdate = yr + "-" + mon + "-" + day;
		
		orm.setCurrentdate(currdate);
		orm.setUploaddate(currdate);
		return (Integer) sessionFactory.getCurrentSession().save(orm);
	}
	
	public void update(OldReportsModel report)
	{
		
		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		mon = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		currdate = yr + "-" + mon + "-" + day;
		report.setCurrentdate(currdate);
		report.setUploaddate(currdate);
		sessionFactory.getCurrentSession().merge(report);
	}
	
	public void delete(int count)
	{
		OldReportsModel c = getById(count);
		sessionFactory.getCurrentSession().delete(c);
	}
	
	
}
