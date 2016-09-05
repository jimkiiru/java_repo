package com.dekutclubs.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.ClubModel;

@Component("clubFormValidator")
public class ClubFormValidator implements Validator {
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return ClubModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chairperson", "required.chairperson",
				"Chairperson name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chairpersoncontact", "required.chairpersoncontact",
				"Chairperson contact is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chairpersonemail", "required.chairpersonemail",
				"Chairperson email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clubname", "required.clubname", "Club name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clubpatron", "required.clubpatron",
				"Patron name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patronEmail", "required.patronEmail",
				"Patron email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patronContact", "required.patronContact",
				"Patron contact is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formationdate", "required.formationdate",
				"Formation date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "meetingvenue", "required.meetingvenue",
				"Meeting venue is required.");
	}

}
