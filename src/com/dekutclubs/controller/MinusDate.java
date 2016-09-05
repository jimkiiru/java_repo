package com.dekutclubs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.dekutclubs.model.*;

@Component
public class MinusDate implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

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
				String clubName="";
				int chairId=0;
				Query qry = session.createQuery("from ClubModel c where c.clubname=?");
				qry.setParameter(0, list1.get(i));
				List l = qry.list();
				Iterator it = l.iterator();
				while (it.hasNext()) {
					Object o = (Object) it.next();
					ClubModel c2 = (ClubModel) o;
					recipientAddress = c2.getChairpersonemail();
					clubName=c2.getClubname();
				}
				Query qry1 = session.createQuery("from ChairModel cm where cm.emailaddress=?");
				qry1.setParameter(0, recipientAddress);
				List list3 = qry1.list();
				Iterator it1 = list3.iterator();
				while (it1.hasNext()) {
					Object o = (Object) it1.next();
					ChairModel c2 = (ChairModel) o;
					chairId=c2.getChairid();
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
					/*String message ="Please Upload Monthly Report!";
					SimpleMailMessage email = new SimpleMailMessage();
					email.setTo(recipientAddress);
					email.setSubject("Late Submission Of Report");
					email.setText(message);
					mailSender.send(email);*/
				    }
					else if (diffDays ==4) {
						SessionFactory factory1 = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
						Session sess = factory1.openSession();
						Transaction tx=sess.beginTransaction();
						String hqlUpdate="update UploadFile l set l.status=:stat where l.clubid=:clubid";
						int updateEntities=sess.createQuery(hqlUpdate).setString("stat","Delayed").setString("clubid",clubName)
								.executeUpdate();
							tx.commit();
							sess.close();
					}else if(diffDays ==8){
						SessionFactory factory1 = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
						Session sess = factory1.openSession();
						Transaction tx=sess.beginTransaction();
						String hqlUpdate="update UploadFile l set l.status=:stat where l.clubid=:clubid";
						int updateEntities=sess.createQuery(hqlUpdate).setString("stat","Deactivated").setString("clubid",clubName)
								.executeUpdate();
						
						String hqlUpdate1="update LoginModel lm set lm.verdict=:stat where lm.cid=:chairid";
						int updateEntities1=sess.createQuery(hqlUpdate1).setString("stat","Deactivated").setInteger("chairid",chairId)
								.executeUpdate();
						String hqlUpdate2="update ClubModel cm set cm.status=:stat where cm.clubname=:cn";
						int updateEntities2=sess.createQuery(hqlUpdate2).setString("stat","Deactivated").setString("cn",clubName)
								.executeUpdate();
							tx.commit();
							sess.close();
					}
					/*if (diffDays == 1) {
						String message ="Please Upload Monthly Report!";
						SimpleMailMessage email = new SimpleMailMessage();
						email.setTo(recipientAddress);
						email.setSubject("Late Submission Of Report");
						email.setText(message);
						mailSender.send(email);
					}*/
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//JOptionPane.showMessageDialog(null, diffDays);
			}
		}
	}
}
