package com.dekutclubs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.dao.*;
import com.dekutclubs.model.*;

@Controller
public class ClubsControllers {
	@Autowired
	private ClubsDAO clubsDAO;

	@Autowired
	ReportsDAO reportsDAO;
	@Autowired
	private ClubFormValidator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	// Clubs
	@RequestMapping("/searchClubs")
	public ModelAndView searchClubs(
			@RequestParam(required = false, defaultValue = "") String clubname) {
		ModelAndView mav = new ModelAndView("showClubs");
		List<ClubModel> clubs = clubsDAO.searchClubs(clubname.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", clubs);
		return mav;
	}

	@RequestMapping("/viewAllClubs")
	public ModelAndView getAllContacts() {
		ModelAndView mav = new ModelAndView("showClubs");
		List<ClubModel> clubs = clubsDAO.getAllClubs();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", clubs);
		return mav;
	}

	@RequestMapping("/viewDeactivatedClubs")
	public String getDeactivatedClub(HttpServletRequest request,
			HttpSession session) {

		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("from ClubModel u where u.status=:cn");
		qry.setString("cn", "Deactivated");
		List list = qry.list();
		request.setAttribute("list", list);

		return "showDeactivatedClubs";
	}

	@RequestMapping(value = "/saveClub", method = RequestMethod.GET)
	public ModelAndView newclubForm(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("newClub");
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
		String chairName = "", emailAddress = "";
		String contact = "";
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("from ChairModel c where c.chairid=:cid");
		qry.setInteger("cid", idChair);
		List l = qry.list();
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Object o = (Object) it.next();
			ChairModel p = (ChairModel) o;

			chairName = p.getFirst_name();
			contact = p.getCellphone();
			emailAddress = p.getEmailaddress();
		}

		request.setAttribute("chair", chairName);
		request.setAttribute("contact", contact);
		request.setAttribute("email", emailAddress);

		SessionFactory fact = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session ses = fact.openSession();

		Query qry1 = ses
				.createQuery("select c.categoryname from ClubCategoryModel c");
		List l1 = qry1.list();
		request.setAttribute("category", l1);

		ClubModel club = new ClubModel();
		mav.getModelMap().put("newClub", club);
		return mav;
	}

	@RequestMapping(value = "/saveClub", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("newClub") ClubModel club,
			BindingResult result, SessionStatus status, HttpSession ses,
			HttpServletRequest request) {
		validator.validate(club, result);
		if (result.hasErrors()) {
			ModelAndView mav1 = new ModelAndView("newClub");
			String chairpid = "" + ses.getAttribute("cid");
			int idChair = Integer.parseInt(chairpid);
			String chairName = "", emailAddress = "";
			String contact = "";
			SessionFactory factory = new AnnotationConfiguration().configure(
					"hibernate.cfg.xml").buildSessionFactory();
			Session sess = factory.openSession();

			Query qry = sess
					.createQuery("from ChairModel c where c.chairid=:cid");
			qry.setInteger("cid", idChair);
			List l = qry.list();
			Iterator it = l.iterator();
			while (it.hasNext()) {
				Object o = (Object) it.next();
				ChairModel p = (ChairModel) o;

				chairName = p.getFirst_name();
				contact = p.getCellphone();
				emailAddress = p.getEmailaddress();
			}

			request.setAttribute("chair", chairName);
			request.setAttribute("contact", contact);
			request.setAttribute("email", emailAddress);

			Query qry1 = sess
					.createQuery("select c.categoryname from ClubCategoryModel c");
			List l1 = qry1.list();
			request.setAttribute("category", l1);
			mav1.addObject("successMess", "Failed");
			return mav1;
		}

		String chairpid = "" + ses.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);

		SessionFactory fact = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = fact.openSession();

		Query qry1 = sess.createQuery("from ClubModel c where c.clubname=:cn");
		qry1.setString("cn", club.getClubname());
		List l1 = qry1.list();
		Iterator it1 = l1.iterator();
		while (it1.hasNext()) {
			ModelAndView mav = new ModelAndView("newClub");

			String chairName = "", emailAddress = "";
			String contact = "";

			Query qury = sess
					.createQuery("from ChairModel c where c.chairid=:cid");
			qury.setInteger("cid", idChair);
			List li = qury.list();
			Iterator ite = li.iterator();
			while (ite.hasNext()) {
				Object o = (Object) ite.next();
				ChairModel p = (ChairModel) o;

				chairName = p.getFirst_name();
				contact = p.getCellphone();
				emailAddress = p.getEmailaddress();
			}

			request.setAttribute("chair", chairName);
			request.setAttribute("contact", contact);
			request.setAttribute("email", emailAddress);

			Query qry2 = sess
					.createQuery("select c.categoryname from ClubCategoryModel c");
			List l2 = qry2.list();
			request.setAttribute("category", l2);
			mav.addObject("successMess", "Club Already Exist");
			return mav;
		}

			club.setChairid(idChair);
			clubsDAO.save(club);

			String clubname = "";
			SessionFactory factory = new AnnotationConfiguration().configure(
					"hibernate.cfg.xml").buildSessionFactory();
			Session session = factory.openSession();
			Query query = session
					.createQuery("from ClubModel c where c.clubid=?");
			query.setParameter(0, club.getClubid());

			List list = query.list();
			Iterator itr = list.iterator();

			if (itr.hasNext()) {
				Object o = (Object) itr.next();
				clubname = club.getClubname();
			}

			UploadFile uf = new UploadFile();
			uf.setClubid(clubname);
			uf.setUrl("");
			uf.setCurrentdate("");
			uf.setUploaddate("");

			reportsDAO.save(uf);
			ModelAndView mav = new ModelAndView("newClub");

			String clubName = "", chairName = "", emailAddress = "";
			String contact = "";

			Query qury = sess
					.createQuery("from ChairModel c where c.chairid=:cid");
			qury.setInteger("cid", idChair);
			List li = qury.list();
			Iterator ite = li.iterator();
			while (ite.hasNext()) {
				Object o = (Object) ite.next();
				ChairModel p = (ChairModel) o;
			
				chairName = p.getFirst_name();
				contact = p.getCellphone();
				emailAddress = p.getEmailaddress();
			}

			request.setAttribute("chair", chairName);
			request.setAttribute("contact", contact);
			request.setAttribute("email", emailAddress);

			Query qry2 = sess
					.createQuery("select c.categoryname from ClubCategoryModel c");
			List l2 = qry2.list();
			request.setAttribute("category", l2);

			mav.addObject("successMess", "Data sent");

			status.setComplete();
			return mav;
		
	}

	@RequestMapping(value = "/activateClub", method = RequestMethod.GET)
	public String activate(@RequestParam("clubid") Integer clubid) {

		String clubName = "";
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from ClubModel a where a.clubid=?");
		query.setParameter(0, clubid);

		List list = query.list();
		Iterator itr = list.iterator();

		if (itr.hasNext()) {
			Object o = (Object) itr.next();
			ClubModel a = (ClubModel) o;
			clubName = a.getClubname();
		}
		int chairid = 0;
		Query query1 = session
				.createQuery("from ChairModel a where a.clubName=?");
		query1.setParameter(0, clubName);

		List list1 = query1.list();
		Iterator itr1 = list1.iterator();

		if (itr1.hasNext()) {
			Object o = (Object) itr1.next();
			ChairModel a = (ChairModel) o;
			chairid = a.getChairid();
		}

		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update LoginModel l set l.verdict=:status where l.cid=:lgid";
		int updateEntities = session.createQuery(hqlUpdate)
				.setString("status", "Approved").setInteger("lgid", chairid)
				.executeUpdate();

		String hqlUpdate1 = "update ClubModel c set c.status=:status where c.clubname=:cname";
		int updateEntities1 = session.createQuery(hqlUpdate1)
				.setString("status", "Active").setString("cname", clubName)
				.executeUpdate();
		String hqlUpdate2 = "update UploadFile u set u.status=:status where u.clubid=:cname";
		int updateEntities2 = session.createQuery(hqlUpdate2)
				.setString("status", "Active").setString("cname", clubName)
				.executeUpdate();

		tx.commit();
		session.close();
		return "showDeactivatedClubs";
	}

	@RequestMapping(value = "/updateClub", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("clubid") Integer clubid) {
		ModelAndView mav = new ModelAndView("editClub");
		ClubModel club = clubsDAO.getById(clubid);
		mav.addObject("editClub", club);
		return mav;
	}

	@RequestMapping(value = "/updateClub", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("editClub") ClubModel contact,
			BindingResult result, SessionStatus status) {
		validator.validate(contact, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("editClub");
			return mav;
		}
		clubsDAO.update(contact);
		status.setComplete();
		ModelAndView mav = new ModelAndView("editClub");
		mav.addObject("successMess", "Club registered successfully");
		return mav;

	}

	@RequestMapping("deleteClub")
	public ModelAndView delete(@RequestParam("clubid") Integer clubid) {
		ModelAndView mav = new ModelAndView("redirect:viewAllClubs.do");
		clubsDAO.delete(clubid);
		return mav;
	}
	// End of club DAO

	// Reports

}
