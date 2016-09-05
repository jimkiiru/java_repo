package com.dekutclubs.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.model.*;

@Controller
public class ResetPasswordController {

	private String token;
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/forgotpass", method = RequestMethod.GET)
	public ModelAndView forgotPass() {
		ModelAndView mav = new ModelAndView("forgotPassword");
		ForgotPassModel forgot = new ForgotPassModel();
		mav.getModelMap().put("forgotPassword", forgot);
		return mav;
	}
	@RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
	public ModelAndView getPass(@ModelAttribute("forgotPassword") ForgotPassModel forgot, BindingResult result) {
						
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
		
		SessionFactory factory1 = new AnnotationConfiguration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		Session sess = factory1.openSession();
		Transaction tx = sess.beginTransaction();
		String hqlUpdate = "update LoginModel l set l.password=:pass where l.username=:user";
		int updateEntities = sess.createQuery(hqlUpdate).setString("pass", token)
				.setString("user", forgot.getUsername()).executeUpdate();
		tx.commit();
		String recipientAddress = forgot.getUsername();
		String mess="Dear chairperson, Your DeKUT Clubs account password has been reset to: "+token;
		SimpleMailMessage email1 = new SimpleMailMessage();
		email1.setTo(recipientAddress);
		email1.setSubject("Password Reset for DeKUT Clubs account.");
		email1.setText(mess);
		mailSender.send(email1);
		
		ModelAndView mav=new ModelAndView("forgotPassword");
		mav.addObject("mess","Your new password has been mailed to you.");
		
		return mav;
	}
	
	@RequestMapping(value = "/resetpass", method = RequestMethod.GET)
	public ModelAndView resetPass() {
		ModelAndView mav = new ModelAndView("RPassword");
		ResetPasswordModel pass = new ResetPasswordModel();
		mav.getModelMap().put("RPassword", pass);
		return mav;
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public ModelAndView getNewPaswd(@Valid @ModelAttribute("RPassword") ResetPasswordModel reset, BindingResult result,
			ResetPasswordModel u,HttpServletRequest request, HttpSession session) {

		// String cahirpersonid = "" + session.getAttribute("chairId");
		// int chairId = Integer.parseInt(cahirpersonid);
		// String chid=""+session.getAttribute("cid");
		// int cid=Integer.parseInt(chid);
		if (result.hasErrors()) {
			ModelAndView mav1 = new ModelAndView("RPassword");			
			return mav1;
		}
		
		String oldPassword="";
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session sess = sessionFactory.openSession();
		Transaction tx = sess.beginTransaction();
		
		Query query = sess.createQuery("from LoginModel a where a.username=?");	
		query.setParameter(0, reset.getEmailaddress());

		List list = query.list();
		Iterator itr = list.iterator();

		if (itr.hasNext()) {
			Object o = (Object) itr.next();
			LoginModel a = (LoginModel) o;
			oldPassword = a.getPassword();
		}
		if (oldPassword.equals(reset.getOldpassword())) {
			String hqlUpdate = "update ChairModel c set c.password=:pass where c.emailaddress=:email";
			int updateEntities = sess.createQuery(hqlUpdate).setString("pass", reset.getPassword())
					.setString("email", reset.getEmailaddress()).executeUpdate();
			String hqlUpdate1 = "update LoginModel l set l.password=:passw where l.username=:user";
			int updateEntities1 = sess.createQuery(hqlUpdate1).setString("passw", reset.getPassword())
					.setString("user", reset.getEmailaddress()).executeUpdate();
			tx.commit();
			sess.close();
			ModelAndView model = new ModelAndView("RPassword");
			model.addObject("returnMessage", "You have successfully reset your password");
			return model;
		} else {
			ModelAndView mav = new ModelAndView("RPassword");
			mav.addObject("returnMessage", "Old password didn't match!Please try again.");
			return mav;
		}
	}
}
