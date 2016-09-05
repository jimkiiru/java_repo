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
public class ReportsDAO
{
	int yr, mon, day;
	String currdate = "";
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UploadFile getById(int count)
	{
		return (UploadFile) sessionFactory.getCurrentSession().get(UploadFile.class, count);
	}
	
	@SuppressWarnings("unchecked")
	public List<UploadFile> searchReports(String clubid)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadFile.class);
		criteria.add(Restrictions.ilike("clubid", clubid+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UploadFile> getAllReports()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UploadFile.class);
		return criteria.list();
	}
	
	public int save(UploadFile report)
	
	{
		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		mon = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		currdate = yr + "-" + mon + "-" + day;
		
		report.setCurrentdate(currdate);
		report.setUploaddate(currdate);
		return (Integer) sessionFactory.getCurrentSession().save(report);
	}
	
	public void update(UploadFile report)
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
		UploadFile c = getById(count);
		sessionFactory.getCurrentSession().delete(c);
	}
}
