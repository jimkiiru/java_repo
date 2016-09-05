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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

import com.dekutclubs.dao.MemberDAO;
import com.dekutclubs.model.ChairModel;
import com.dekutclubs.model.DisaproveCommentModel;
import com.dekutclubs.model.LoginModel;
import com.dekutclubs.model.MemberModel;
import com.dekutclubs.model.SelectClubModel;
import com.dekutclubs.model.SelectionModel;
import com.dekutclubs.model.StudentModel;

@Controller
@Transactional
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private MemberFormValidator validator;
	int yr;
	String year = "";

	String fName = "", lName = "", surname = "", regNum = "";

	// Clubs
	@RequestMapping("/searchMember")
	public ModelAndView searchClubs(
			@RequestParam(required = false, defaultValue = "") String regno) {
		ModelAndView mav = new ModelAndView("showMembers");
		List<MemberModel> members = memberDAO.searchMembers(regno.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", members);
		return mav;
	}

	@RequestMapping(value = "/selectmenu", method = RequestMethod.GET)
	public ModelAndView viewMembersByYear(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("selection");

		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("select c.clubname from ClubModel c");
		List l = qry.list();
		request.setAttribute("clubs", l);

		SelectionModel member = new SelectionModel();
		mav.getModelMap().put("selection", member);
		return mav;
	}

	@RequestMapping(value = "/selectclub", method = RequestMethod.GET)
	public ModelAndView viewclubOfficials(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("selectClub");

		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("select c.clubname from ClubModel c");
		List l = qry.list();
		request.setAttribute("clubs", l);

		SelectionModel member = new SelectionModel();
		mav.getModelMap().put("selectClub", member);
		return mav;
	}

	@RequestMapping(value = "/selectclub2", method = RequestMethod.POST)
	public String viewClubOfficials(@Valid SelectClubModel scm,
			BindingResult result, SessionStatus status,
			HttpServletRequest request) throws Exception {
		String clubname = scm.getClubName().toUpperCase();
		request.setAttribute("clubname", clubname);

		ModelAndView mav = new ModelAndView("showOfficials");
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess
				.createQuery("from MemberModel m where m.clubName=? and m.yearOfRegistration=? "
						+ "and m.position=?");
		qry.setString(0, scm.getClubName());
		qry.setString(1, scm.getYear());
		qry.setString(2, "chairperson");

		List l = qry.list();
		request.setAttribute("list", l);
		Query qry1 = sess
				.createQuery("from MemberModel m where m.clubName=:cn and m.yearOfRegistration=:yor and m.position=:p");
		qry1.setString("cn", scm.getClubName());
		qry1.setString("yor", scm.getYear());
		qry1.setString("p", "treasurer");
		List l1 = qry1.list();
		request.setAttribute("list1", l1);

		Query qry2 = sess
				.createQuery("from MemberModel m where m.clubName=:cn and m.yearOfRegistration=:yor and m.position=:p");
		qry2.setString("cn", scm.getClubName());
		qry2.setString("yor", scm.getYear());
		qry2.setString("p", "secretary");
		List l2 = qry2.list();
		request.setAttribute("list2", l2);

		Query qry3 = sess
				.createQuery("from MemberModel m where m.clubName=:cn and m.yearOfRegistration=:yor and m.position=:p");
		qry3.setString("cn", scm.getClubName());
		qry3.setString("yor", scm.getYear());
		qry3.setString("p", "Vice-chairperson");
		List l3 = qry3.list();
		request.setAttribute("list3", l3);

		Query qry4 = sess
				.createQuery("from MemberModel m where m.clubName=:cn and m.yearOfRegistration=:yor and m.position=:p");
		qry4.setString("cn", scm.getClubName());
		qry4.setString("yor", scm.getYear());
		qry4.setString("p", "Organizing Secretary");
		List l4 = qry4.list();
		request.setAttribute("list4", l4);

		Query qry5 = sess
				.createQuery("from MemberModel m where m.clubName=:cn and m.yearOfRegistration=:yor and m.position=:p");
		qry5.setString("cn", scm.getClubName());
		qry5.setString("yor", scm.getYear());
		qry5.setString("p", "Committee member");
		List l5 = qry5.list();
		request.setAttribute("list5", l5);
		status.setComplete();
		return "showOfficials";
	}

	@RequestMapping("/viewAllMembers")
	public String getMembers(HttpServletRequest request, HttpSession session) {
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
		String clubName = "";
		// ModelAndView mav = new ModelAndView("showMembers");
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query query = sess.createQuery("from ChairModel p where p.chairid=:id");
		query.setInteger("id", idChair);
		List l = query.list();
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Object o = (Object) it.next();
			ChairModel p = (ChairModel) o;
			// clubName = p.getClubName();
		}

		Query qry = sess.createQuery("from MemberModel m where m.clubName=:cn");
		qry.setString("cn", clubName);
		List list = qry.list();
		request.setAttribute("list", list);

		return "showMembersChair";
	}

	@RequestMapping(value = "/selectmen2", method = RequestMethod.POST)
	public String DisApprove(@Valid SelectionModel sm, BindingResult result,
			SessionStatus status, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("showMembers");
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess
				.createQuery("from MemberModel m where m.clubName=:cn and m.yearOfRegistration=:yor");
		qry.setString("cn", sm.getClubName());
		qry.setString("yor", sm.getYear());
		List l = qry.list();

		request.setAttribute("list", l);
		status.setComplete();
		return "showMembers";
	}

	@RequestMapping(value = "/saveMember", method = RequestMethod.GET)
	public ModelAndView newclubForm(HttpServletRequest request,HttpSession session) {
		ModelAndView mav = new ModelAndView("newMember");
		
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);

		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();
		Query qry = sess
				.createQuery("select c.clubname from ClubModel c where c.chairid=:cid");
		qry.setInteger("cid", idChair);
		List l = qry.list();
		request.setAttribute("clubname", l);
		
		MemberModel member = new MemberModel();
		mav.getModelMap().put("newMember", member);
		return mav;
	}

	@RequestMapping(value = "/saveMember", method = RequestMethod.POST)
	public ModelAndView create(
			@Valid @ModelAttribute("newMember") MemberModel member,
			BindingResult result, SessionStatus status, HttpSession session) {

		validator.validate(member, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newMember");
			return mav;
		}

		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);

		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		year = "" + yr;

		member.setYearOfRegistration(year);

		SQLQuery query1 = sess
				.createSQLQuery("select u.FirstName from members u where u.CellPhone=:pno");
		query1.setString("pno", member.getCellphone());
		List list1 = query1.list();
		Iterator it2 = list1.iterator();
		while (it2.hasNext()) {
			ModelAndView mav = new ModelAndView("newMember");
			mav.addObject("successMess",
					"Member with phone number. " + member.getCellphone()
							+ " already exist!");
			return mav;
		}
		SQLQuery query2 = sess
				.createSQLQuery("select u.FirstName from members u where u.RegNo=:reg ");
		query2.setString("reg", member.getRegno());
		List list2 = query2.list();
		Iterator it1 = list2.iterator();
		while (it1.hasNext()) {
			ModelAndView mav = new ModelAndView("newMember");
			mav.addObject("successMess",
					"Member with reg no. " + member.getRegno()
							+ " already exists!");
			return mav;
		}

		memberDAO.save(member);
		status.setComplete();
		ModelAndView mav = new ModelAndView("newMember");
		mav.addObject("successMess", "Member added successfully");
		return mav;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("memberid") Integer memberid) {
		ModelAndView mav = new ModelAndView("editMember");
		MemberModel member = memberDAO.getById(memberid);
		mav.addObject("editMember", member);
		return mav;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public ModelAndView update(
			@ModelAttribute("editMember") MemberModel member,
			BindingResult result, SessionStatus status, HttpSession session) {
		validator.validate(member, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("editMember");
			return mav;
		}

		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
		SessionFactory factory = new AnnotationConfiguration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		year = "" + yr;

		member.setYearOfRegistration(year);

		memberDAO.update(member);
		status.setComplete();
		ModelAndView mav = new ModelAndView("editMember");
		mav.addObject("successMess", "Data updated successfully");
		return mav;
	}

	@RequestMapping("deleteMember")
	public ModelAndView delete(@RequestParam("memberid") Integer memberid) {
		ModelAndView mav = new ModelAndView("redirect:viewAllMembers.do");
		memberDAO.delete(memberid);
		return mav;
	}

}