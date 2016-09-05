package com.dekutclubs.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
public class AdminController {

	private String token;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private AdminFormValidator validator;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView model = new ModelAndView("logout");
		return model;
	}

	
	@RequestMapping(value = "/admindash", method = RequestMethod.GET)
	public ModelAndView adminDashbord() {

		ModelAndView model = new ModelAndView("adminDashboard");		
		return model;
	}

	@Scope("session")
	@RequestMapping(value = "/chair", method = RequestMethod.GET)
	public ModelAndView chairPerson(HttpServletRequest request,HttpSession session) {
		ModelAndView model = new ModelAndView("chairpersonDashboard");
		
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
		String fName = "";
		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query query = sess.createQuery("from ChairModel p where p.chairid=:id");
		query.setInteger("id", idChair);
		List l = query.list();
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Object o = (Object) it.next();
			ChairModel p = (ChairModel) o;
			fName = p.getFirst_name();
		}
		request.setAttribute("fname", fName.toUpperCase());
		request.setAttribute("chairid", idChair);
		
		return model;
	}

	@RequestMapping("/searchAdmin")
	public ModelAndView searchAdmins(@RequestParam(required = false, defaultValue = "") String first_name) {
		ModelAndView mav = new ModelAndView("showAdmins");
		List<AdminModel> admin = adminDAO.searchAdmin(first_name.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", admin);
		return mav;
	}

	@RequestMapping("/viewAllAdmins")
	public ModelAndView getAllAdmins() {
		ModelAndView mav = new ModelAndView("showAdmins");
		List<AdminModel> admin = adminDAO.getAllAdmins();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", admin);
		return mav;
	}

	@RequestMapping(value = "/saveAdmin", method = RequestMethod.GET)
	public ModelAndView newClubForm() {
		ModelAndView mav = new ModelAndView("newAdmin");
		AdminModel admin = new AdminModel();
		mav.getModelMap().put("newAdmin", admin);
		return mav;
	}

	@RequestMapping(value = "/saveAdmin", method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("newAdmin") AdminModel admin, BindingResult result, LoginModel u,
			SessionStatus status) throws MalformedURLException {
		validator.validate(admin, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newAdmin");
			return mav;
		}
		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		t.begin();

		String email = "", paswd = "", state = "";

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

		String name = admin.getFirst_name();
		String recipientAddress = admin.getEmailaddress();		

		admin.setPassword(token);
		int i = (Integer) adminDAO.save(admin);

		if (i > 0) {
			Query query = session.createQuery("from AdminModel p where p.emailaddress=:email");
			query.setString("email", admin.getEmailaddress());
			List list = query.list();

			Iterator itr = list.iterator();

			while (itr.hasNext()) {
				Object o = (Object) itr.next();
				AdminModel p = (AdminModel) o;

				email = p.getEmailaddress();
				paswd = p.getPassword();
				state = p.getStatus();

			}
			u.setUsername(email);
			u.setPassword(paswd);
			u.setStatus(state);
			session.save(u);
			t.commit();
			session.close();

		} else {

		}

		status.setComplete();
		URL myUrl = new URL("http://localhost:8080/DekutClubManager/loginform.do");
		// creates a simple e-mail object
		SimpleMailMessage emailadress = new SimpleMailMessage();
		emailadress.setTo(recipientAddress);
		emailadress.setSubject("Dekut Clubs\n Login credentials.");
		emailadress.setText("Hi," + name + "\n Your username is " + recipientAddress + " and password is " + token
				+ "\n" + "Click the link below to login to your account \n" + myUrl);

		// sends the e-mail
		mailSender.send(emailadress);

		ModelAndView mav = new ModelAndView("newAdmin");

		mav.addObject("successMess", "Admin added successfully");
		return mav;
	}

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("adminid") Integer adminid) {
		ModelAndView mav = new ModelAndView("editAdmin");
		AdminModel admin = adminDAO.getById(adminid);
		mav.addObject("editAdmin", admin);
		return mav;
	}

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	public String update(@ModelAttribute("editAdmin") AdminModel admin, BindingResult result, SessionStatus status) {
		validator.validate(admin, result);
		if (result.hasErrors()) {
			return "editAdmin";
		}
		adminDAO.update(admin);
		status.setComplete();
		return "redirect:viewAllAdmins.do";
	}

	@RequestMapping("deleteAdmin")
	public ModelAndView delete(@RequestParam("adminid") Integer adminid) {
		ModelAndView mav = new ModelAndView("redirect:viewAllAdmins.do");
		adminDAO.delete(adminid);
		return mav;
	}
}
