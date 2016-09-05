package com.dekutclubs.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.dao.*;
import com.dekutclubs.model.*;

@Controller
public class ChairController {
	@Autowired
	private JavaMailSender mailSender;

	private String token;

	@Autowired
	private ChairDAO chairDAO;

	@Autowired
	private ChairFormValidator validator;
	
	int yr=0;

	String year="";
	String fName = "", lName = "", surname = "", regNum = "";

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView regSuccess() {
		ModelAndView model = new ModelAndView("registrationSuccess");
		return model;
	}

	@RequestMapping("/searchChair")
	public ModelAndView searchChairs(
			@RequestParam(required = false, defaultValue = "") String first_name) {
		ModelAndView mav = new ModelAndView("showChairs");
		List<ChairModel> chair = chairDAO.searchChair(first_name.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", chair);
		return mav;
	}

	@RequestMapping("/viewAllChairs")
	public ModelAndView getAllAdmins() {
		ModelAndView mav = new ModelAndView("showChairs");
		List<ChairModel> chair = chairDAO.getAllChairs();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", chair);
		return mav;
	}

	@RequestMapping("/profile")
	public ModelAndView getChairs(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("showChair");
		
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
		String clubName = "";
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query query = sess.createQuery("from ChairModel p where p.chairid=:id");
		query.setInteger("id", idChair);
		List l = query.list();		
		request.setAttribute("list", l);

		return mav;
	}

	@RequestMapping(value = "/saveChair", method = RequestMethod.GET)
	public ModelAndView newClubForm(@RequestParam("regNo") String regNo,
			HttpServletRequest request, HttpSession session) {
		
		SessionFactory factory = new AnnotationConfiguration().configure(
				"students.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("from StudentModel s where s.regNo=:reg");
		qry.setString("reg", regNo);
		List l = qry.list();
		Iterator it = l.iterator();
		if (it.hasNext()) {
			Object o = (Object) it.next();
			StudentModel s = (StudentModel) o;
			regNum = s.getRegNo();
			fName = s.getfName();
			lName = s.getlName();
			surname = s.getSurName();
		}else{
			ModelAndView mav1 = new ModelAndView("enterRegnum");
			mav1.addObject("Mess", "Reg Number Does Not Exist.");
			return mav1;
		}

			request.setAttribute("regnum", regNum);
			request.setAttribute("fname", fName);
			request.setAttribute("lname", lName);
			request.setAttribute("surname", surname);

			//sess.close();
			ModelAndView mav = new ModelAndView("newChair");
			ChairModel chair = new ChairModel();
			mav.getModelMap().put("newChair", chair);
			return mav;
		

	}

	@RequestMapping(value = "/saveChair", method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("newChair") ChairModel chair,
			BindingResult result, LoginModel u,MemberModel mm, HttpServletRequest request,SessionStatus status)
			throws MalformedURLException {
		
		validator.validate(chair, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newChair");
			request.setAttribute("regnum", regNum);
			request.setAttribute("fname", fName);
			request.setAttribute("lname", lName);
			request.setAttribute("surname", surname);
			return mav;
		}
		SessionFactory factor = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factor.openSession();
		String reg="",phonenum="",email1="";
		Query qry1 = sess.createQuery("from ChairModel c where c.regnum=:reg");
		qry1.setString("reg", chair.getRegnum());
		List l1 = qry1.list();
		Iterator it1 = l1.iterator();
		while(it1.hasNext()) {
			ModelAndView mav = new ModelAndView("newChair");
			request.setAttribute("regnum", regNum);
			request.setAttribute("fname", fName);
			request.setAttribute("lname", lName);
			request.setAttribute("surname", surname);

			mav.addObject("successMess", "A student with that reg no. Already exist");
			return mav;
		}
		Query qry2 = sess.createQuery("from ChairModel c where c.emailaddress=:email");
		qry2.setString("email", chair.getEmailaddress());
		List l2 = qry2.list();
		Iterator it2 = l2.iterator();
		while(it2.hasNext()) {
			ModelAndView mav = new ModelAndView("newChair");
			request.setAttribute("regnum", regNum);
			request.setAttribute("fname", fName);
			request.setAttribute("lname", lName);
			request.setAttribute("surname", surname);

			mav.addObject("successMess", "A student with that Email. Already exist");
			return mav;
		}
		Query qry3= sess.createQuery("from ChairModel c where c.cellphone=:cell");
		qry3.setString("cell", chair.getCellphone());
		List l3 = qry3.list();
		Iterator it3 = l3.iterator();
		while(it3.hasNext()) {
			ModelAndView mav = new ModelAndView("newChair");
			request.setAttribute("regnum", regNum);
			request.setAttribute("fname", fName);
			request.setAttribute("lname", lName);
			request.setAttribute("surname", surname);

			mav.addObject("successMess", "A student with that Phone Number. Already exist");
			return mav;
		}
		
		
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		t.begin();
		String emailadress = "", paswd = "", state = "";

		Random rn = new Random();
		int range = 9999999 - 1000000 + 1;
		int randomNum = rn.nextInt(range) + 1000000; // For 7 digit number

		Random rc = new Random();
		char c = (char) (rc.nextInt(26) + 'A');

		Random df = new Random();
		char h = (char) (df.nextInt(26) + 'A');

		Random sm = new Random();
		char ss = (char) (df.nextInt(26) + 'a');

		System.out.println(c);

		token = h + "" + ss + "" + randomNum + "" + c;

		String name = chair.getFirst_name();
		String recipientAddress = chair.getEmailaddress();
		int cid = 0;		
		chair.setPassword(token);
		int i = (Integer) chairDAO.save(chair);

		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		year = "" + yr;
		if (i > 0) {
			Query query = session
					.createQuery("from ChairModel p where p.emailaddress=:email");
			query.setString("email", chair.getEmailaddress());
			List list = query.list();

			Iterator itr = list.iterator();

			while (itr.hasNext()) {
				Object o = (Object) itr.next();
				ChairModel p = (ChairModel) o;

				cid = p.getChairid();
				emailadress = p.getEmailaddress();
				paswd = p.getPassword();
				state = p.getStatus();
				
			}
			u.setCid(cid);
			u.setUsername(emailadress);
			u.setPassword(paswd);
			u.setStatus(state);
			u.setVerdict("NULL");

			session.save(u);
			
			t.commit();
			session.close();

		} else {

		}

		status.setComplete();

		URL myUrl = new URL(
				"http://localhost:8080/DekutClubManager/loginform.do");
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject("Dekut Clubs\n Login credentials.");
		email.setText("Hi," + name + "\n Your username is "
				+ chair.getEmailaddress() + " and password is " + token + "\n"
				+ "Click the link below to login to your account \n" + myUrl);

		// sends the e-mail
		mailSender.send(email);
		ModelAndView mav = new ModelAndView("newChair");

		mav.addObject("successMess", "You have registered successfully");
		return mav;
		
	}

	@RequestMapping(value = "/updateChair", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("chairid") Integer chairid) {
		ModelAndView mav = new ModelAndView("editChair");
		ChairModel chair = chairDAO.getById(chairid);
		mav.addObject("editChair", chair);
		return mav;
	}

	@RequestMapping(value = "/updateChair", method = RequestMethod.POST)
	public String update(@ModelAttribute("editChair") ChairModel chair,
			BindingResult result, SessionStatus status) {
		validator.validate(chair, result);
		if (result.hasErrors()) {
			return "editAdmin";
		}
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		String hqlUpdate = "update ChairModel c set c.first_name=:fn,c.lname=:ln,c.emailaddress=:email,c.cellphone=:cp,"
				+ "c.clubName=:cn where c.chairid=:cid";
		int updateEntities = sess.createQuery(hqlUpdate).setString("fn", chair.getFirst_name())
				.setString("ln", chair.getLname()).setString("email", chair.getEmailaddress())
				.setString("cp",chair.getCellphone()).executeUpdate();
		tx.commit();
		sess.close();
		
		chairDAO.update(chair);
		status.setComplete();
		return "redirect:viewAllChairs.do";
	}

	@RequestMapping("deleteChair")
	public ModelAndView delete(@RequestParam("chairId") Integer chairId) {
		ModelAndView mav = new ModelAndView("redirect:viewAllChairs.do");
		chairDAO.delete(chairId);
		return mav;
	}
}
