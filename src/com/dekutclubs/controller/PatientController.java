package com.dekutclubs.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.dekutclubs.dao.PatientDAO;
import com.dekutclubs.model.PatientModel;

@Controller
@Transactional
public class PatientController {
	@Autowired
	private PatientDAO patientDAO;

	
	@RequestMapping(value = "/savePatient", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("newPatient") PatientModel patient,BindingResult result, SessionStatus status) {

		patientDAO.save(patient);
		
		ModelAndView mav = new ModelAndView("newPatient");
		mav.addObject("successMess", "Category added successfully");
		status.setComplete();
		return mav;
	}

}
