package com.dekutclubs.controller;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dekutclubs.model.*;


@Component("categoryFormValidator")
public class CategoryFormValidator implements Validator
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz)
	{
		return ClubCategoryModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryname","required.categoryname", "Category name is required.");
	}

}