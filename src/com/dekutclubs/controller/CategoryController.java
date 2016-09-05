package com.dekutclubs.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dekutclubs.dao.*;
import com.dekutclubs.model.*;

@Controller
public class CategoryController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private CategoryFormValidator validator;
	
	@RequestMapping("/searchCategory")
	public ModelAndView searchCategories(@RequestParam(required = false, defaultValue = "") String categoryname) {
		ModelAndView mav = new ModelAndView("showCategories");
		List<ClubCategoryModel> category = categoryDAO.searchCategories(categoryname.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY",category);
		return mav;
	}

	@RequestMapping("/viewAllCategories")
	public ModelAndView getAllCategories() {
		ModelAndView mav = new ModelAndView("showCategories");
		List<ClubCategoryModel> category = categoryDAO.getAllCategories();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", category);
		return mav;
	}
	@RequestMapping(value = "/selectCat", method = RequestMethod.GET)
	public ModelAndView selectC() {
		ModelAndView mav = new ModelAndView("selectCategory");
		ClubCategoryModel category = new ClubCategoryModel();
		mav.getModelMap().put("selectCategory", category);
		return mav;
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.GET)
	public ModelAndView newClubForm() {
		ModelAndView mav = new ModelAndView("newCategory");
		ClubCategoryModel category = new ClubCategoryModel();
		mav.getModelMap().put("newCategory", category);
		return mav;
	}
	
	@RequestMapping(value = "/selectcategory", method = RequestMethod.GET)
	public ModelAndView selectCategory(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("selectCategory");

		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("select c.categoryname from ClubCategoryModel c");
		List l = qry.list();
		request.setAttribute("category", l);

		ClubCategoryModel club = new ClubCategoryModel();
		mav.getModelMap().put("selectCategory", club);
		return mav;
	}
	@RequestMapping(value = "/selectcategory2", method = RequestMethod.GET)
	public ModelAndView viewCategoryClubs(@Valid ClubCategoryModel category,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("showClubs");

		SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session sess = factory.openSession();

		Query qry = sess.createQuery("from ClubModel c where c.category=:cat");
		qry.setString("cat", category.getCategoryname());
		List l = qry.list();
		request.setAttribute("category", l);

		return mav;
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("newCategory") ClubCategoryModel category,BindingResult result, SessionStatus status) {
		
		validator.validate(category, result);
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("newCategory");
			return mav;
		}
		categoryDAO.save(category);
		ModelAndView mav = new ModelAndView("newCategory");
		mav.addObject("successMess", "Category added successfully");
		status.setComplete();
		return mav;
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("categoryid") Integer categoryid) {
		ModelAndView mav = new ModelAndView("editCategory");
		ClubCategoryModel category = categoryDAO.getById(categoryid);
		mav.addObject("editCategory", category);
		return mav;
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String update(@ModelAttribute("editCategory") ClubCategoryModel category, BindingResult result, SessionStatus status) {
		validator.validate(category, result);
		if (result.hasErrors()) {
			return "editCategory";
		}
		categoryDAO.update(category);
		status.setComplete();
		return "redirect:viewAllCategories.do";
	}

	@RequestMapping("deleteCategory")
	public ModelAndView delete(@RequestParam("categoryid") Integer categoryid) {
		ModelAndView mav = new ModelAndView("redirect:viewAllCategories.do");
		categoryDAO.delete(categoryid);
		return mav;
	}
}
