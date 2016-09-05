package com.dekutclubs.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dekutclubs.model.*;

@Controller
public class ClubDataTable {
	/*
	   @RequestMapping(value = "/clubConst", method = 
			    RequestMethod.GET)
		    public String printWelcome(@ModelAttribute("showConstitution") ConstitutionModel person, BindingResult result,ModelMap model, HttpServletRequest 
			    request, HttpServletResponse response) {
			
		    	return "showConstitution";

		    }*/

		    @RequestMapping(value = "/springPaginationDataTables", method = RequestMethod.GET)
		    public @ResponseBody String springPaginationDataTables(HttpServletRequest  request) throws IOException {
				
		    	//Fetch the page number from client
		    	Integer pageNumber = 0;
		    	if (null != request.getParameter("iDisplayStart"))
		    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;		
		    	
		    	//Fetch search parameter
		    	String searchParameter = request.getParameter("sSearch");
		    	
		    	//Fetch Page display length
		    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		    	
		    	//Create page list data
		    	List<ConstitutionModel> personsList = createPaginationData(pageDisplayLength);
				
				//Here is server side pagination logic. Based on the page number you could make call 
				//to the data base create new list and send back to the client. For demo I am shuffling 
				//the same list to show data randomly
				if (pageNumber == 1) {
					Collections.shuffle(personsList);
				}else if (pageNumber == 2) {
					Collections.shuffle(personsList);
				}else {
					Collections.shuffle(personsList);
				}
				
				//Search functionality: Returns filtered list based on search parameter
				personsList = getListBasedOnSearchParameter(searchParameter,personsList);
				
				
				ClubJsonObject personJsonObject = new ClubJsonObject();
				//Set Total display record
				personJsonObject.setiTotalDisplayRecords(500);
				//Set Total record
				personJsonObject.setiTotalRecords(500);
				personJsonObject.setAaData(personsList);
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(personJsonObject);
			
				return json2;
		    }

			private List<ConstitutionModel> getListBasedOnSearchParameter(String searchParameter,List<ConstitutionModel> personsList) {
				
				
				if (null != searchParameter && !searchParameter.equals("")) {
					List<ConstitutionModel> personsListForSearch = new ArrayList<ConstitutionModel>();
					searchParameter = searchParameter.toUpperCase();
					for (ConstitutionModel person : personsList) {
						
						if (person.getClubname().toUpperCase().indexOf(searchParameter)!= -1 ) {
							personsListForSearch.add(person);					
						}
						
					}
					personsList = personsListForSearch;
					personsListForSearch = null;
				}
				return personsList;
			}

			private List<ConstitutionModel> createPaginationData(Integer pageDisplayLength) {
				List<ConstitutionModel> personsList = new ArrayList<ConstitutionModel>();
				List<ConstitutionModel> l = new ArrayList<ConstitutionModel>();
				SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
				Session session=sessionFactory.openSession();
				session.beginTransaction();
				
				Query qry = session.createQuery("from ConstitutionModel p ");
				
				
				personsList=qry.list();
				
				
				return personsList;
			}

}
