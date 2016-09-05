package com.dekutclubs.controller;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.MemberModel;


@Component("memberFormValidator")
public class MemberFormValidator implements Validator
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz)
	{
		return MemberModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "regno","required.regno", "Registration number is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position","required.position", "Member position is required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailaddress","required.emailaddress", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellphone","required.cellphone", "Phone number is required.");
	}

}