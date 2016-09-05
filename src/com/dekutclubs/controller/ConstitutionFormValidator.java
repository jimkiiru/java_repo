package com.dekutclubs.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.*;

@Component("constitutionFormValidator")
public class ConstitutionFormValidator implements Validator
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz)
	{
		return ConstitutionModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clubname","required.clubname", "Club name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url","required.url", "Please choose a file is required.");
	}

}
