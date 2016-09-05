package com.dekutclubs.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.dao.StudentDAO;
import com.dekutclubs.model.ChairModel;
import com.dekutclubs.model.DisaproveCommentModel;
import com.dekutclubs.model.LoginModel;
import com.dekutclubs.model.StudentModel;
import com.dekutclubs.model.SelectionModel;

@Controller
@Transactional
public class StudentController {
	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentFormValidator validator;

	int yr;
	String year = "";

	// Clubs
	
	@RequestMapping(value = "/enterReg", method = RequestMethod.GET)
	public ModelAndView enterRegnum() {
		ModelAndView mav = new ModelAndView("enterRegnum");	
		return mav;
	}


	@RequestMapping(value = "/saveStudent", method = RequestMethod.GET)
	public ModelAndView newclubForm() {
		
		ModelAndView mav = new ModelAndView("newStudent");		
		StudentModel Student = new StudentModel();
		mav.getModelMap().put("newStudent", Student);
		return mav;
	}

	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("newStudent") StudentModel student, BindingResult result, SessionStatus status,
			HttpSession session) {
		validator.validate(student, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newStudent");
			return mav;
		}
		Configuration config=new AnnotationConfiguration().configure("students.cfg.xml");
		SessionFactory sessionFact=config.buildSessionFactory();
		Session sess=sessionFact.openSession();
		sess.beginTransaction();
		
		sess.save(student);
		
		sess.getTransaction().commit();
		
		ModelAndView mav = new ModelAndView("newStudent");
		mav.addObject("successMess", "Data Saved.");
		
		status.setComplete();
		return mav;
	}
}