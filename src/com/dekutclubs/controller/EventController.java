package com.dekutclubs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.dao.*;
import com.dekutclubs.model.*;

@Controller
public class EventController
{
	
	private static final int BUFFER_SIZE = 4096;
   private String filePath;
   private String url;
   private List l;
	private String saveDirectory = "C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\events\\";
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private EventFormValidator validator;

	@RequestMapping("/searchEvents")
	public ModelAndView searchRepots(@RequestParam(required = false, defaultValue = "") String eventid) {
		ModelAndView mav = new ModelAndView("showReports");
		List<EventModel> events = eventDAO.searchEvents(eventid.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", events);
		return mav;
	}
	
	@RequestMapping("/viewAllEvents")
	public ModelAndView getAllEvents()
	{
		ModelAndView mav = new ModelAndView("showEvents");
		List<EventModel> events = eventDAO.getAllEvents();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", events);
		return mav;
	}
	@RequestMapping("/viewAllEventChair")
	public String getEventsChair(HttpServletRequest request,HttpSession session) {
	String chairpid = "" + session.getAttribute("cid");
	int idChair = Integer.parseInt(chairpid);
	String clubName = "";
	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session sess = factory.openSession();

	Query query = sess.createQuery("select p.clubname from ClubModel p where p.chairid=:id");
	query.setInteger("id", idChair);
	List l = query.list();
	
	for(int i=0;i<l.size();i++){
	Query qry = sess.createQuery("from EventModel e where e.clubname=?");
	qry.setParameter(0,l.get(i) );
	List list = qry.list();
	request.setAttribute("list", list);
	}
	
	
	return "showEventsChair";
	}
	
	@RequestMapping(value="/saveEvent", method=RequestMethod.GET)
	public ModelAndView newEventsForm(HttpServletRequest request,HttpSession session)
	{
		ModelAndView mav = new ModelAndView("newEvent");
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
	
		SessionFactory factory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session sess=factory.openSession();
		Query qry = sess.createQuery("select c.clubname from ClubModel c where c.chairid=:cid");
		qry.setInteger("cid", idChair);
		l = qry.list();
		request.setAttribute("clubname", l);
		
		
		EventModel event = new EventModel();
		mav.getModelMap().put("newEvent", event);
		return mav;
	}
	
	@RequestMapping(value="/saveEvent", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("newEvent")EventModel event, BindingResult result, SessionStatus status,
			@RequestParam CommonsMultipartFile[] fileUpload,HttpSession session,HttpServletRequest request) throws IllegalStateException, IOException
	{

		
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				event.setUrl(aFile.getOriginalFilename());
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
				}
			}
		}
		validator.validate(event, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newEvent");
			//mav.addObject("successMess", "Successful");
			return mav;
		}
		eventDAO.save(event);
		ModelAndView mav = new ModelAndView("newEvent");
		request.setAttribute("clubname", l);
		mav.addObject("successMess", "Event Uploaded Succesfully");

		status.setComplete();
		return mav;
	}
	
	@RequestMapping(value = "/downloadEvent", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
            HttpServletResponse response)throws IOException {
		
		 url=request.getParameter("url");
		 
		 System.out.println("File name is: " +url);
	        // get absolute path of the application
	    	ServletContext context = request.getSession().getServletContext();
	        // construct the complete absolute path of the file
	        String fullPath ="C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\events\\"+url+"";      
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
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
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
	
	@RequestMapping(value="/updateEvent", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("eventid")Integer eventid)
	{
		ModelAndView mav = new ModelAndView("editEvent");
		EventModel event = eventDAO.getById(eventid);
		mav.addObject("editEvent", event);
		return mav;
	}
	
	@RequestMapping(value="/updateEvent", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("editEvent") EventModel event, BindingResult result, SessionStatus status,
			@RequestParam CommonsMultipartFile[] fileUpload)
	{
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				event.setUrl(aFile.getOriginalFilename());
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
		validator.validate(event, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("editEvent");
			return mav;
		}
		eventDAO.update(event);
		status.setComplete();
		ModelAndView mav = new ModelAndView("editEvent");
		mav.addObject("successMess","Data updated successfully");
		return mav;
	}
	
	@RequestMapping("deleteEvent")
	public ModelAndView delete(@RequestParam("eventid")Integer eventid)
	{
		ModelAndView mav = new ModelAndView("redirect:viewAllEvents.do");
		eventDAO.delete(eventid);
		return mav;
	}

}