package com.dekutclubs.controller;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.*;


@Component("chairFormValidator")
public class ChairFormValidator implements Validator
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz)
	{
		return AdminModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "regnum","required.regnum", "Registration number is required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname","required.lname", "Last name is required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name","required.first_name", "First Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailaddress","required.emailaddress", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellphone","required.cellphone", "Phone number is required.");
		
	}

}