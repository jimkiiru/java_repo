package com.dekutclubs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.naming.AuthenticationException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

import com.dekutclubs.dao.*;
import com.dekutclubs.model.*;

@Controller
public class ConstitutionController {

	private static final int BUFFER_SIZE = 4096;

	private String filePath;
	private String url;
	int lgid;
	private SessionFactory sessionFactory;
	HttpSession sess;

	private String saveDirectory = "C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\constitutions\\";
	@Autowired
	private ConstitutionDAO constitutionDAO;
	private ChairDAO chairDAO;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ConstitutionFormValidator validator;

	@RequestMapping("/searchConstitutions")
	public ModelAndView searchRepots(@RequestParam(required = false, defaultValue = "") String clubname) {
		ModelAndView mav = new ModelAndView("showConstitution");
		List<ConstitutionModel> constitution = constitutionDAO.searchConstitutions(clubname.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", constitution);
		return mav;
	}

	@RequestMapping("/viewAllConstitutions")
	public ModelAndView getAllConstitutions() {
		ModelAndView mav = new ModelAndView("showConstitution");
		List<ConstitutionModel> constitution = constitutionDAO.getAllConstitutions();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", constitution);
		return mav;
	}

	@RequestMapping(value = "/saveConstitution", method = RequestMethod.GET)
	public ModelAndView newConstitutionForm(HttpServletRequest request,HttpSession session) {
		ModelAndView mav = new ModelAndView("newConstitution");
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
	
		SessionFactory factory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session sess=factory.openSession();
		Query qry = sess.createQuery("select c.clubname from ClubModel c where c.chairid=:cid");
		qry.setInteger("cid", idChair);
		List l = qry.list();
		request.setAttribute("clubname", l);
		
		ConstitutionModel constitution = new ConstitutionModel();
		mav.getModelMap().put("newConstitution", constitution);
		return mav;
	}
	@RequestMapping(value = "/saveConstitution", method = RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute("newConstitution") ConstitutionModel constitution, BindingResult result,
			SessionStatus status, HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload,
			HttpSession session) throws Exception {

		String cahirpersonid = "" + session.getAttribute("chairId");
		int chairId = Integer.parseInt(cahirpersonid);

		constitution.setChairid(chairId);
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				constitution.setUrl(aFile.getOriginalFilename());
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
				}
			}
		}
		validator.validate(constitution, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newConstitution");
			return mav;
			// return "newConstitution";
		}
		constitutionDAO.save(constitution);

		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();
		String username = "";
		Query qry = sess.createQuery("from LoginModel p where p.cid=:id");
		qry.setInteger("id", chairId);

		List l = qry.list();

		Iterator it = l.iterator();

		while (it.hasNext()) {
			Object o = (Object) it.next();
			LoginModel p = (LoginModel) o;
			username = p.getUsername();
		}

		String recipientAddress = "jamesmalika92@gmail.com";
		String message = "Your constitution has reached office of the dean of students and is being processed\n"
				+ "Keep cheking your email for updates.Thank you,stay connected.\n" + "Constitution sent by:"
				+ username;
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject("Dekut Clubs\n Constitutions.");
		email.setText(message);
		mailSender.send(email);
		ModelAndView mav = new ModelAndView("newConstitution");
		mav.addObject("successMess", "Constitution uploaded successfully.");
		status.setComplete();
		return mav;
	}

	@RequestMapping(value = "/updateConstitution", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("fieldid") Integer fieldid) {
		ModelAndView mav = new ModelAndView("editConstitution");
		ConstitutionModel constitution = constitutionDAO.getById(fieldid);
		mav.addObject("editConstitution", constitution);
		return mav;
	}

	@RequestMapping(value = "/downloadConstitution", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		url = request.getParameter("url");

		System.out.println("File name is: " + url);
		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();

		// construct the complete absolute path of the file
		String fullPath = "C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\constitutions\\" + url + "";
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}

	@RequestMapping(value = "/approveConstitution", method = RequestMethod.GET)
	public ModelAndView approve(@RequestParam("logid") Integer logid,
			@ModelAttribute("newConstitution") ConstitutionModel constitution, BindingResult result,
			SessionStatus status) throws Exception {

		String recipientAddress = "";
		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from LoginModel a where a.logid=?");
		query.setParameter(0, logid);

		List list = query.list();
		Iterator itr = list.iterator();

		if (itr.hasNext()) {
			Object o = (Object) itr.next();
			LoginModel a = (LoginModel) o;
			recipientAddress = a.getUsername();
		}

		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update LoginModel l set l.verdict=:status where l.logid=:lgid";
		int updateEntities = session.createQuery(hqlUpdate).setString("status", "Approved").setInteger("lgid", logid)
				.executeUpdate();
		tx.commit();
		session.close();

		URL myUrl = new URL("http://localhost:8080/DekutClubManager/chair.do");
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject("Dekut Clubs\nConstitution approval details.");
		email.setText(
				"\n Your constitution has been approved.Your now proceed as per instructions on your dashboard via the link below.\n"
						+ myUrl);

		// sends the e-mail
		mailSender.send(email);
		status.setComplete();
		ModelAndView mav = new ModelAndView("newConstitution");
		mav.addObject("successMess", "Constitution approved successfully");
		return mav;
	}

	@RequestMapping(value = "/disApproveConstitutio", method = RequestMethod.GET)
	public ModelAndView disapprove(@RequestParam("logid") Integer logid) {
		lgid = logid;
		ModelAndView mav = new ModelAndView("disapproveComment");
		DisaproveCommentModel disapprove = new DisaproveCommentModel();
		mav.getModelMap().put("disapproveComment", disapprove);
		return mav;
	}

	@RequestMapping(value = "/disApproveConstitution", method = RequestMethod.GET)
	public ModelAndView DisApprove(@Valid DisaproveCommentModel disaprove, BindingResult result, SessionStatus status)
			throws Exception {

		// int logid=(int) sess.getAttribute("logid");
		String recipientAddress = "";
		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from LoginModel a where a.logid=?");
		query.setParameter(0, lgid);

		List list = query.list();
		Iterator itr = list.iterator();

		if (itr.hasNext()) {
			Object o = (Object) itr.next();
			LoginModel a = (LoginModel) o;
			recipientAddress = a.getUsername();
		}

		Transaction tx = session.beginTransaction();
		String hqlUpdate = "update LoginModel l set l.verdict=:status where l.logid=:lgid";
		int updateEntities = session.createQuery(hqlUpdate).setString("status", "DisApproved").setInteger("lgid", lgid)
				.executeUpdate();
		tx.commit();
		session.close();
		String commentmsg = disaprove.getComment();
		System.out.println(commentmsg);
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject("Dekut Clubs\n Disapproval details.\n");
		email.setText("Reasons for club disapproval:\n" + commentmsg);

		// sends the e-mail
		mailSender.send(email);
		status.setComplete();
		ModelAndView mav = new ModelAndView("showConstitution");
		mav.addObject("successMess", "Constitution disapproved successfully.");
		return mav;
	}
	@RequestMapping(value = "/updateConstitution", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("editConstitution") ConstitutionModel constitution, BindingResult result,
			SessionStatus status, @RequestParam CommonsMultipartFile[] fileUpload) {
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				constitution.setUrl(aFile.getOriginalFilename());
				if (!aFile.getOriginalFilename().equals("")) {
					try {
						aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		validator.validate(constitution, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("editConstitution");
			return mav;
		}
		constitutionDAO.update(constitution);
		status.setComplete();
		ModelAndView mav = new ModelAndView("editConstitution");
		mav.addObject("successMess","Data updated successfully");
		return mav;
	}

	@RequestMapping("deleteConstitution")
	public ModelAndView delete(@RequestParam("fieldid") Integer fieldid) {
		ModelAndView mav = new ModelAndView("redirect:viewAllConstitutions.do");
		constitutionDAO.delete(fieldid);
		return mav;
	}

}
