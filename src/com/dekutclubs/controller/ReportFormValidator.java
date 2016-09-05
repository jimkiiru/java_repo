package com.dekutclubs.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.ClubModel;

@Component("reportFormValidator")
public class ReportFormValidator implements Validator
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clubid","required.id", "Club id is required.");
	}

}
