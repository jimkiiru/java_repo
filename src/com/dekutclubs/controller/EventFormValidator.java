package com.dekutclubs.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.*;

@Component("eventFormValidator")
public class EventFormValidator implements Validator
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz)
	{
		return EventModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventName","required.eventName", "Event name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventPlace","required.eventPlace", "Place of event is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url","required.url", "Please choose a file is required.");
	}

}
