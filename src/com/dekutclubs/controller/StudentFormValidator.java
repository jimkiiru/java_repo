package com.dekutclubs.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.ClubModel;

@Component("studentFormValidator")
public class StudentFormValidator implements Validator
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz)
	{
		return ClubModel.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "regNo","required.regNo", "Registration number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName","required.fName", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName","required.lName", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNum","required.phoneNum", "Phone number is required.");
	}

}
