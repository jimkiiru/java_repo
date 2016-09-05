package com.dekutclubs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.io.FileOutputStream;

import com.dekutclubs.dao.*;
import com.dekutclubs.model.*;

@Controller
public class ReportsController {
	
	private static final int BUFFER_SIZE = 4096;
	private String filePath;
	private String url;
	String clubName="";
	List l;

	private String saveDirectory = "C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\monthlyreports\\";
	@Autowired
	private ReportsDAO reportsDAO;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private OldReportsDAO oldReportsDAO;

	@Autowired
	private ReportFormValidator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@RequestMapping("/index")
	public ModelAndView days() {
		String currentDate = "";
		String currentHour = "";
		int diffDays = 0;

		Calendar c = new GregorianCalendar();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);

		int hour = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		int millisec = c.get(Calendar.MILLISECOND);

		currentDate = year + "-0" + month + "-" + day;

		String uploadDate = "";

		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Query query1 = session.createQuery("select u.clubid from UploadFile u");
		List list1 = query1.list();
		String maxCount = "";

		for (int i = 0; i < list1.size(); i++) {

			SQLQuery query2 = session.createSQLQuery("select max(u.Count) from uploadedfile u where u.ClubId=?");
			query2.setParameter(0, list1.get(i));
			List list2 = query2.list();
			for (int j = 0; j < list2.size(); j++) {
				Query query = session.createQuery("from UploadFile u where u.clubid=? and u.count=?");
				query.setParameter(0, list1.get(i));
				query.setParameter(1, list2.get(j));
				List list = query.list();
				Iterator itr = list.iterator();

				if (itr.hasNext()) {
					Object o = (Object) itr.next();
					UploadFile a = (UploadFile) o;
					uploadDate = a.getUploaddate();
				}

				String recipientAddress = "";
				String clubName = "";
				int chairId = 0;
				Query qry = session.createQuery("from ClubModel c where c.clubname=?");
				qry.setParameter(0, list1.get(i));
				List l = qry.list();
				Iterator it = l.iterator();
				while (it.hasNext()) {
					Object o = (Object) it.next();
					ClubModel c2 = (ClubModel) o;
					recipientAddress = c2.getChairpersonemail();
					clubName = c2.getClubname();
				}
				Query qry1 = session.createQuery("from ChairModel cm where cm.emailaddress=?");
				qry1.setParameter(0, recipientAddress);
				List list3 = qry1.list();
				Iterator it1 = list3.iterator();
				while (it1.hasNext()) {
					Object o = (Object) it1.next();
					ChairModel c2 = (ChairModel) o;
					chairId = c2.getChairid();
				}

				String format = "yyyy-MM-d";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date dateObj1 = null;
				try {
					dateObj1 = sdf.parse(currentDate);
					Date dateObj2 = sdf.parse(uploadDate);
					long diff = dateObj1.getTime() - dateObj2.getTime();
					diffDays = (int) (diff / (24 * 60 * 60 * 1000));
					if (diffDays == 34) {

						String message = "Polite Reminder, Please upload your club's monthly report.";
						SimpleMailMessage email = new SimpleMailMessage();
						email.setTo(recipientAddress);
						email.setSubject("Club's monthly report");
						email.setText(message);
						mailSender.send(email);
					} else if (diffDays == 4) {
						SessionFactory factory1 = new AnnotationConfiguration().configure("hibernate.cfg.xml")
								.buildSessionFactory();
						Session sess = factory1.openSession();
						Transaction tx = sess.beginTransaction();
						String hqlUpdate = "update UploadFile l set l.status=:stat where l.clubid=:clubid";
						int updateEntities = sess.createQuery(hqlUpdate).setString("stat", "Delayed")
								.setString("clubid", clubName).executeUpdate();
						tx.commit();

						String message = "Kindly upload club's monthly report.You have only15 days remaining for your club to be deactivated!";
						SimpleMailMessage email = new SimpleMailMessage();
						email.setTo(recipientAddress);
						email.setSubject("Club's monthly report delay.");
						email.setText(message);
						//mailSender.send(email);
						sess.close();
					} else if (diffDays == 6) {
						SessionFactory factory1 = new AnnotationConfiguration().configure("hibernate.cfg.xml")
								.buildSessionFactory();
						Session sess = factory1.openSession();
						Transaction tx = sess.beginTransaction();
						String hqlUpdate = "update UploadFile l set l.status=:stat where l.clubid=:clubid";
						int updateEntities = sess.createQuery(hqlUpdate).setString("stat", "Deactivated")
								.setString("clubid", clubName).executeUpdate();
						String hqlUpdate1 = "update LoginModel lm set lm.verdict=:stat where lm.cid=:chairid";
						int updateEntities1 = sess.createQuery(hqlUpdate1).setString("stat", "Deactivated")
								.setInteger("chairid", chairId).executeUpdate();
						String hqlUpdate2 = "update ClubModel cm set cm.status=:stat where cm.clubname=:cn";
						int updateEntities2 = sess.createQuery(hqlUpdate2).setString("stat", "Deactivated")
								.setString("cn", clubName).executeUpdate();
						tx.commit();
						String message = "Sorry!Your club has been deactivated.Kindly visit dean's office for further clarification";
						SimpleMailMessage email = new SimpleMailMessage();
						email.setTo(recipientAddress);
						email.setSubject("Club deactivation.");
						email.setText(message);
						//mailSender.send(email);
						sess.close();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(diffDays);
			}
		}
		ModelAndView md = new ModelAndView("home");
		return md;
	}

	@RequestMapping("/searchReports")
	public ModelAndView searchRepots(@RequestParam(required = false, defaultValue = "") String uploaddate) {
		ModelAndView mav = new ModelAndView("showReports");
		List<UploadFile> reports = reportsDAO.searchReports(uploaddate.trim());
		mav.addObject("list", reports);
		return mav;
	}
	@RequestMapping("/searchReportAdmin")
	public ModelAndView searchRepotsAdmin(@RequestParam(required = false, defaultValue = "") String clubid) {
		ModelAndView mav = new ModelAndView("showReportsAdmin");
		List<UploadFile> reports = reportsDAO.searchReports(clubid.trim());
		mav.addObject("list", reports);
		return mav;
	}
	
	@RequestMapping(value="/pdf", method=RequestMethod.GET)
	public ModelAndView creatPdf(HttpSession sess,HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
     Document doc = new Document();
     PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\monthlyreports\\Report.pdf"));
     doc.open();

      String schName="DEDAN KIMATHI UNIVERSITY OF TECHNOLOGY";
     PdfPTable table = new PdfPTable(1);

     Image img = Image.getInstance("C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\logo\\dekutlogo.jpg");
     img.setAlignment(Element.ALIGN_CENTER);

     img.scaleAbsolute(100, 50);
     doc.add(img);
     
     Paragraph preface = new Paragraph("" + schName + "",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     preface.setAlignment(Element.ALIGN_CENTER);
     doc.add(preface);

     Paragraph infoo = new Paragraph("P.O BOX 657 10100-NYERI KENYA",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     infoo.setAlignment(Element.ALIGN_CENTER);
     doc.add(infoo);
     
     Paragraph inf = new Paragraph("CELL PHONE: 0708680879, TELKOM 061-2050000 Ext. 1262",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK));
     inf.setAlignment(Element.ALIGN_CENTER);
     doc.add(inf);
     
     Paragraph time = new Paragraph("Email: deanofstudents@dkut.ac.ke",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     time.setAlignment(Element.ALIGN_CENTER);
     doc.add(time);
     doc.add(new Paragraph("."));
     Paragraph tim = new Paragraph("OFFICE OF THE DEAN OF STUDENTS",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     tim.setAlignment(Element.ALIGN_CENTER);
     doc.add(tim);
    
     doc.add(new Paragraph("______________________________________________________________________________"));
     
     Paragraph tm = new Paragraph("CLUB SOCIETY REGISTRATION FORM",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     tm.setAlignment(Element.ALIGN_CENTER);
     doc.add(tm);
     
     String chairpid = "" + sess.getAttribute("cid");
 	int idChair = Integer.parseInt(chairpid);
 	String clubName = "",currDate="";
 	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
 	Session session = factory.openSession();

 	Query query = session.createQuery("from ChairModel p where p.chairid=:id");
 	query.setInteger("id", idChair);
 	List l = query.list();
 	Iterator it = l.iterator();
 	while (it.hasNext()) {
 		Object o = (Object) it.next();
 		ChairModel p = (ChairModel) o;
 		//clubName = p.getClubName();
 	}
 	Query query2 = session.createQuery("from ConstitutionModel c where c.clubname=:cn");
 	query2.setString("cn", clubName);
 	List l2 = query2.list();
 	Iterator it2 = l2.iterator();
 	while (it2.hasNext()) {
 		Object o = (Object) it2.next();
 		ConstitutionModel p = (ConstitutionModel) o;
 		currDate = p.getCurrentDate();
 	}
 	
 	int count=0;
 	Query query3 = session.createQuery("from MemberModel m where m.clubName=:cn");
 	query3.setString("cn", clubName);
 	List l3 = query3.list();
 	Iterator it3 = l3.iterator();
 	while (it3.hasNext()) {
 		Object o = (Object) it3.next();
 		count++;
 	}
     int mid=0;
     String fname="",position="",regno="",cellphone="";
 	Query query4 = session.createQuery("from MemberModel m where m.position=:p and m.clubName=:cn");
 	query4.setString("p","chairperson");
 	query4.setString("cn",clubName);
 	List l4 = query4.list();
 	Iterator it4 = l4.iterator();
 	while (it4.hasNext()) {
 		Object o = (Object) it4.next();
 		MemberModel p = (MemberModel) o;
 		mid= p.getMemberid();
 		cellphone=p.getCellphone();
 		fname=p.getFname();
 		position=p.getPosition();
 		regno=p.getRegno();
 	}
 	int mid2=0;
    String fname2="",position2="",regno2="",cellphone2="";
	Query query5 = session.createQuery("from MemberModel m where m.position=:p and m.clubName=:cn");
	query5.setString("p","treasurer");
	query5.setString("cn",clubName);
	List l5= query5.list();
	Iterator it5= l5.iterator();
	while (it5.hasNext()) {
		Object o = (Object) it5.next();
		MemberModel p = (MemberModel) o;
		mid2= p.getMemberid();
		cellphone2=p.getCellphone();
		fname2=p.getFname();
		position2=p.getPosition();
		regno2=p.getRegno();
	}
	int mid3=0;
    String fname3="",position3="",regno3="",cellphone3="";
	Query query6 = session.createQuery("from MemberModel m where m.position=:p and m.clubName=:cn");
	query6.setString("p","secretary");
	query6.setString("cn",clubName);
	List l6= query6.list();
	Iterator it6= l6.iterator();
	while (it6.hasNext()) {
		Object o = (Object) it6.next();
		MemberModel p = (MemberModel) o;
		mid3= p.getMemberid();
		cellphone3=p.getCellphone();
		fname3=p.getFname();
		position3=p.getPosition();
		regno3=p.getRegno();
	}
	
	int midv=0;
    String fnamev="",positionv="",regnov="",cellphonev="";
	Query queryv = session.createQuery("from MemberModel m where m.position=:p and m.clubName=:cn");
	queryv.setString("p","Vice-chairperson");
	queryv.setString("cn",clubName);
	List lv= queryv.list();
	Iterator itv= lv.iterator();
	while (itv.hasNext()) {
		Object o = (Object) itv.next();
		MemberModel p = (MemberModel) o;
		midv= p.getMemberid();
		cellphonev=p.getCellphone();
		fnamev=p.getFname();
		positionv=p.getPosition();
		regnov=p.getRegno();
	}
	int mido=0;
    String fnameo="",positiono="",regnoo="",cellphoneo="";
	Query queryo = session.createQuery("from MemberModel m where m.position=:p and m.clubName=:cn");
	queryo.setString("p","Organizing Secretary");
	queryo.setString("cn",clubName);
	List lo= queryo.list();
	Iterator ito= lo.iterator();
	while (ito.hasNext()) {
		Object o = (Object) ito.next();
		MemberModel p = (MemberModel) o;
		mido= p.getMemberid();
		cellphoneo=p.getCellphone();
		fnameo=p.getFname();
		positiono=p.getPosition();
		regnoo=p.getRegno();
	}
	int midc=0;
    String fnamec="",positionc="",regnoc="",cellphonec="";
	Query queryc = session.createQuery("from MemberModel m where m.position=:p and m.clubName=:cn");
	queryc.setString("p","Committee member");
	queryc.setString("cn",clubName);
	List lc= queryc.list();
	Iterator itc= lc.iterator();
	while (itc.hasNext()) {
		Object o = (Object) itc.next();
		MemberModel p = (MemberModel) o;
		midc= p.getMemberid();
		cellphonec=p.getCellphone();
		fnamec=p.getFname();
		positionc=p.getPosition();
		regnoc=p.getRegno();
	}
	
	String pname="",address="";
	Query query7 = session.createQuery("from ClubModel c where c.clubname=:cn");
 	query7.setString("cn", clubName);
 	List l7 = query7.list();
 	Iterator it7 = l7.iterator();
 	while (it7.hasNext()) {
 		Object o = (Object) it7.next();
 		ClubModel p = (ClubModel) o;
 		pname=p.getClubpatron();
 		address=p.getPatronContact();
 	}
 	
     doc.add(new Paragraph("Society/Club name:- "+clubName));
     //doc.add(new Paragraph("Address:- 1116"));
     doc.add(new Paragraph("Date of Application:- "+currDate));
     doc.add(new Paragraph("Current No. of members:- "+count));
     doc.add(new Paragraph("Names of Interim Office Bearers(list below)"));
     
     doc.add(new Paragraph("."));
     
     PdfPTable mean = new PdfPTable(5);
     PdfPCell op = new PdfPCell(new Paragraph("S/NO", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLUE)));
     PdfPCell midd = new PdfPCell(new Paragraph("OFFICE BEARERS", FontFactory.getFont(FontFactory.COURIER_BOLD, 12,  BaseColor.BLUE)));
     PdfPCell endd = new PdfPCell(new Paragraph("POSITION", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLUE)));
     PdfPCell enddM = new PdfPCell(new Paragraph("REG.NO", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLUE)));
     PdfPCell enddK = new PdfPCell(new Paragraph("TELPHONE", FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLUE)));
      midd.setNoWrap(true);
 
     mean.setWidthPercentage(100);
     mean.addCell(op);
     mean.addCell(midd);
     mean.addCell(endd);
     mean.addCell(enddM);
     mean.addCell(enddK);
     
     
     mean.addCell(""+mid);
     mean.addCell(fname);
     mean.addCell(position);
     PdfPCell p1 = new PdfPCell(new Paragraph(regno));
     p1.setNoWrap(true);
     p1.setPadding(5);
     p1.setPadding(1);
     mean.addCell(p1);
     mean.addCell(cellphone);
     
     mean.addCell(""+midv);
     mean.addCell(fnamev);
     mean.addCell(positionv);
     PdfPCell pv = new PdfPCell(new Paragraph(regnov));
     pv.setNoWrap(true);
     pv.setPadding(1);
     mean.addCell(pv);
     mean.addCell(cellphonev);
     
     mean.addCell(""+mid2);
     mean.addCell(fname2);
     mean.addCell(position2);
     PdfPCell p2 = new PdfPCell(new Paragraph(regno2));
     p2.setNoWrap(true);
     p2.setPadding(1);
     mean.addCell(p2);
     mean.addCell(cellphone2);
     
     mean.addCell(""+mid3);
     mean.addCell(fname3);
     mean.addCell(position3);
     PdfPCell p3 = new PdfPCell(new Paragraph(regno3));
     p3.setNoWrap(true);
     p3.setPadding(1);
     mean.addCell(p3);
     mean.addCell(cellphone3);
     
     mean.addCell(""+mido);
     mean.addCell(fnameo);
     mean.addCell(positiono);
     PdfPCell po = new PdfPCell(new Paragraph(regnoo));
     po.setNoWrap(true);
     po.setPadding(1);
     mean.addCell(po);
     mean.addCell(cellphoneo);
     
     mean.addCell(""+midc);
     mean.addCell(fnamec);
     mean.addCell(positionc);
     PdfPCell pc = new PdfPCell(new Paragraph(regnoc));
     pc.setNoWrap(true);
     pc.setPadding(1);
     mean.addCell(pc);
     mean.addCell(cellphonec);
     
     
     doc.add(mean);
     
     doc.add(new Paragraph("."));
     doc.add(new Paragraph("Society/Club Patron:- "+pname));
     doc.add(new Paragraph("Patrons contact/Address:- "+address));
  
     Paragraph pref = new Paragraph("Signed........................................................Date......................................",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     pref.setAlignment(Element.ALIGN_CENTER);
     doc.add(pref);
     
     doc.add(new Paragraph("______________________________________________________________________________"));
     
     Paragraph prf = new Paragraph("DeKUT is ISO 9001:2008 Certified",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     prf.setAlignment(Element.ALIGN_CENTER);
     doc.add(prf);
     Paragraph pr = new Paragraph("Better life through technology",
             FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
     pr.setAlignment(Element.ALIGN_CENTER);
     doc.add(pr);

     doc.close();
	 
	 
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
		

		ServletContext context = request.getSession().getServletContext();

		String fullPath = "C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\monthlyreports\\Report.pdf";
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	 
     ModelAndView model=new ModelAndView("pdf");
	model.addObject("message","new language added successfully");
		
		
		return model;
	}
	
	
	
	@RequestMapping("/viewAllReportChair")
	public String getAllReportChair(HttpServletRequest request,HttpSession session) {
	String chairpid = "" + session.getAttribute("cid");
	int idChair = Integer.parseInt(chairpid);
	String clubName = "";
	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session sess = factory.openSession();

	Query query = sess.createQuery("from ChairModel p where p.chairid=:id");
	query.setInteger("id", idChair);
	List l = query.list();
	Iterator it = l.iterator();
	while (it.hasNext()) {
		Object o = (Object) it.next();
		ChairModel p = (ChairModel) o;
		//clubName = p.getClubName();
	}
	Query qry = sess.createQuery("from OldReportsModel u where u.clubid=:cn  ORDER BY u.uploaddate DESC");
	qry.setString("cn", clubName);
	List list = qry.list();
	request.setAttribute("list", list);
	
	return "showReports";
	}
	
	@RequestMapping("/viewDelayedReport")
	public String getAllDelayedReport(HttpServletRequest request,HttpSession session) {
	
	SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	Session sess = factory.openSession();

	Query qry = sess.createQuery("from UploadFile u where u.status=:cn");
	qry.setString("cn", "Delayed");
	List list = qry.list();
	request.setAttribute("list", list);
	
	return "showDelayedReports";
	}
	
	@RequestMapping("/viewAllReports")
	public String getAllReports(HttpServletRequest request,HttpSession session) {
		
		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("from UploadFile u ORDER BY u.uploaddate DESC");
		List list = qry.list();
		request.setAttribute("list", list);
		
		return "showReportsAdmin";
	}

	@RequestMapping(value = "/saveReport", method = RequestMethod.GET)
	public ModelAndView newreportForm(HttpServletRequest request,HttpSession session) throws ParseException {

		ModelAndView mav = new ModelAndView("newReport");
		
		String chairpid = "" + session.getAttribute("cid");
		int idChair = Integer.parseInt(chairpid);
		
		SessionFactory factory=new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory(); 
		Session sess=factory.openSession();
		
		Query qry = sess.createQuery("select c.clubname from ClubModel c where c.chairid=:cid");
		qry.setInteger("cid", idChair);
		l = qry.list();
		request.setAttribute("clubname", l);	
		
		UploadFile report = new UploadFile();
		mav.getModelMap().put("newReport", report);
		return mav;
	}

	@RequestMapping(value = "/saveReport", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("newReport") UploadFile report, BindingResult result, SessionStatus status,
			HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload,HttpSession sessin) throws Exception {

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				report.setUrl(aFile.getOriginalFilename());
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
				}
			}
		}
		validator.validate(report, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newReport");
			return mav;
		}
		String cahirpersonid = "" + sessin.getAttribute("chairId");
		int chairId = Integer.parseInt(cahirpersonid);
		SessionFactory factory2 = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess1 = factory2.openSession();
		
		Query qry1 = sess1.createQuery("from UploadFile p where p.clubid=:id");
		qry1.setString("id",report.getClubid());
		List l1 = qry1.list();
		Iterator it1= l1.iterator();

		while (!(it1.hasNext())) {
			ModelAndView mav = new ModelAndView("newReport");
			mav.addObject("successMess", "Oops!! Club Is Not Registered");
			return mav;
		}
		
		int yr, mon, day;
		String currdate = "";
		Calendar cal = new GregorianCalendar();
		yr = cal.get(Calendar.YEAR);
		mon = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		currdate = yr + "-" + mon + "-" + day;
		SessionFactory factory1 = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory1.openSession();
		Transaction tx=sess.beginTransaction();
		String hqlUpdate="update UploadFile l set l.url=:url,l.uploaddate=:ud,currentdate=:currd,l.status=:stat where l.clubid=:clubid";
		int updateEntities=sess.createQuery(hqlUpdate)
				.setString("url", report.getUrl()).setString("ud", currdate)
				.setString("currd", currdate).setString("stat","updated")
				.setString("clubid",report.getClubid()).executeUpdate();
			tx.commit();
			sess.close();
		//reportsDAO.save(report);
		
		String clubid="",url="";
		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from UploadFile");
		
		List list = query.list();
		Iterator itr = list.iterator();

		if (itr.hasNext()) {
			Object o = (Object) itr.next();
			//UploadFile u = (UploadFile) o;
			clubid = report.getClubid();
			url=report.getUrl();
			
		}
		OldReportsModel orm=new OldReportsModel();
		orm.setClubid(clubid);
		orm.setUrl(url);
		
		oldReportsDAO.save(orm);
		status.setComplete();
		ModelAndView mav = new ModelAndView("newReport");
		request.setAttribute("clubname", l);
		mav.addObject("successMess", "Report saved successfully");
		return mav;
	}
	
	@RequestMapping(value = "/updateReport", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("count") Integer count) {
		ModelAndView mav = new ModelAndView("editReport");
		UploadFile report = reportsDAO.getById(count);
		mav.addObject("editReport", report);
		return mav;
	}

	@RequestMapping(value = "/downloadReport", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		url = request.getParameter("url");

		System.out.println("File name is: " + url);
		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();

		// construct the complete absolute path of the file
		String fullPath = "C:\\Users\\james kiiru\\kepler_workspace\\Dekut\\monthlyreports\\" + url + "";
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

	@RequestMapping(value = "/updateReport", method = RequestMethod.POST)
	public String update(@ModelAttribute("editReport") UploadFile report, BindingResult result, SessionStatus status,
			@RequestParam CommonsMultipartFile[] fileUpload) {
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());
				report.setUrl(aFile.getOriginalFilename());
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

		validator.validate(report, result);
		if (result.hasErrors()) {
			return "editReport";
		}
		reportsDAO.update(report);
		status.setComplete();
		return "redirect:viewAllReports.do";
	}
	@RequestMapping("deleteReport")
	public ModelAndView delete(@RequestParam("count") Integer count) {
		ModelAndView mav = new ModelAndView("redirect:viewAllReports.do");
		reportsDAO.delete(count);
		return mav;
	}
	// End of Report DAO

}
