package com.dekutclubs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.model.*;
import com.dekutclubs.service.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;


@Controller
@RequestMapping("loginform")
public class LoginController {

	@Autowired
	public LoginService loginService;

	protected SessionFactory sessionFactory;
	String status="";
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "loginform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processForm(@Valid LoginForm loginForm,LoginModel u ,BindingResult result,
			Map model,HttpSession session,HttpServletRequest request) {
		boolean userExists = loginService.checkLogin(loginForm.getUserName(),loginForm.getPassword());
		if(userExists==true){
			SessionFactory factory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session sess=factory.openSession();
			String username="";
			int chairId=0;
			String verdict="";
			Query qry = sess.createQuery("from LoginModel p where p.username=:user and p.password=:pass");
			 qry.setString("user", loginForm.getUserName());
			 qry.setString("pass", loginForm.getPassword());
			List l =qry.list();
			Iterator it = l.iterator();
			while(it.hasNext())
			{
				Object o = (Object)it.next();
				LoginModel p = (LoginModel)o;			
				chairId=p.getLogid();
			}		
	 
			session.setAttribute("chairId", chairId);	
			model.put("loginForm", loginForm);
			ModelAndView mav = new ModelAndView("adminDashboard");
			return mav;
			//return "adminDashboard";
		}else{
			
			SessionFactory factory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
			Session sess=factory.openSession();
			String username="";
			int chairId=0;
			String verdict="";
			Query qry = sess.createQuery("from LoginModel p where p.username=:user and p.password=:pass");
			 qry.setString("user", loginForm.getUserName());
			 qry.setString("pass", loginForm.getPassword());
			List l =qry.list();
			Iterator it = l.iterator();
			while(it.hasNext())
			{
				Object o = (Object)it.next();
				LoginModel p = (LoginModel)o;			
				chairId=p.getLogid();
				verdict=p.getVerdict();
			}		
	 
			session.setAttribute("chairId", chairId);		
			int cid=0;
			Query qry2 = sess.createQuery("from LoginModel c where c.username=:user and c.password=:pass");
			 qry.setString("user", loginForm.getUserName());
			 qry.setString("pass", loginForm.getPassword());
			List l2 =qry.list();	
			Iterator it2 = l2.iterator();
			while(it2.hasNext())
			{
				Object o = (Object)it2.next();
				LoginModel p = (LoginModel)o;				
				cid=p.getCid();
			}
			session.setAttribute("cid", cid);
			
			model.put("loginForm", loginForm);
			if(verdict.equals("Approved")){	
				ModelAndView mav = new ModelAndView("chairpersonDashboard");
				String fName = "";
				Query query = sess.createQuery("from ChairModel p where p.chairid=:id");
				query.setInteger("id", cid);
				List l1 = query.list();
				Iterator it1 = l1.iterator();
				while (it1.hasNext()) {
					Object o = (Object) it1.next();
					ChairModel p = (ChairModel) o;
					fName = p.getFirst_name();
				}
				request.setAttribute("fname", fName.toUpperCase());
				request.setAttribute("chairid", cid);
				return mav;
				//return "redirect:chair.do";
			}else if(verdict.equalsIgnoreCase("null")){
				ModelAndView mav = new ModelAndView("newConstitution");
				ConstitutionModel constitution = new ConstitutionModel();
				
				SessionFactory sfactory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory(); 
				Session sessi=sfactory.openSession();
				
				Query q = sessi.createQuery("select c.clubname from ClubModel c where c.chairpersonemail=:email");
				q.setString("email", loginForm.getUserName());
				List tl = q.list();
							
				request.setAttribute("clubname", tl);
				mav.getModelMap().put("newConstitution", constitution);
				return mav;
			}
			else if(verdict.equalsIgnoreCase("Deactivated")){
				ModelAndView mav = new ModelAndView("loginform");
				mav.addObject("errorMess", "Sorry!Your account has been deactivated!");
				return mav;
			}
			else if(verdict.equalsIgnoreCase("DisApproved")){
				ModelAndView mav = new ModelAndView("newConstitution");
				ConstitutionModel constitution = new ConstitutionModel();
			
				SessionFactory sfactory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory(); 
				Session sessi=sfactory.openSession();
				
				Query q = sessi.createQuery("select c.clubname from ClubModel c where c.chairpersonemail=:email");
				q.setString("email", loginForm.getUserName());
				List tl = q.list();
							
				request.setAttribute("clubname", tl);			
				
				mav.getModelMap().put("newConstitution", constitution);
				return mav;
			}else{
				ModelAndView mav = new ModelAndView("loginform");
				mav.addObject("errorMess", "Invalid username or password!");
				return mav;
			}
			
		}

	}
}
