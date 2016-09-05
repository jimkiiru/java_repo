package com.dekutclubs.controller;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.*;


@Component("adminFormValidator")
public class AdminFormValidator implements Validator
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name","required.first_name", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname","required.lname", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailaddress","required.emailaddress", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellphone","required.cellphone", "Phone number is required.");
	}
}