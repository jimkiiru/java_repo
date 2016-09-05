package com.dekutclubs.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dekutclubs.model.*;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import java.util.Iterator;
import java.util.List;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO{
     
			 
       @Resource(name="sessionFactory")
       protected SessionFactory sessionFactory;

       String status;
       public void setSessionFactory(SessionFactory sessionFactory) {
              this.sessionFactory = sessionFactory;
       }
      
       protected Session getSession(){
              return sessionFactory.openSession();
       }
       
       public int getLogId(String username){
    	   Session session = sessionFactory.openSession();
    	   int logid=0;
			Query qry = session.createQuery("from LoginModel p where p.username=:pass");
			 qry.setString("pass", username);
			List l =qry.list();
			
			Iterator it = l.iterator();
	 
			while(it.hasNext())
			{
				Object o = (Object)it.next();
				LoginModel p = (LoginModel)o;
				
				logid=p.getLogid();
				
			}
			
    	   return logid; 
       }
       

       public boolean checkLogin(String username, String password){
			System.out.println("In Check login");
			Session session = sessionFactory.openSession();
			boolean userFound = false;
			//Query using Hibernate Query Language
			
			
			String SQL_QUERY ="select a.status from LoginModel as a where a.username=? and a.password=?";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,username);
			query.setParameter(1,password);
			List list = query.list();
			Iterator itr=list.iterator();
			
			if(itr.hasNext()){
				Object o=itr.next();
				status=""+o;
			}
			
			if(status.equals("admin")){
			if ((list != null) && (list.size() > 0)) {
				userFound= true;
			}
			}else if(status.equals("chair")){
				
				if ((list != null) && (list.size() > 0)) {
					userFound= false;
				}						  
			}
			return userFound;			            
       }
}
