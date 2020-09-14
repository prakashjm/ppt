package com.xyz.mydiary.validation.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xyz.mydiary.dto.UserDTO;
import com.xyz.mydiary.service.UserService;

public class UserEmailValidation implements ConstraintValidator<ValidUserEmail, UserDTO> {

	@Autowired
	UserService service;

	@Override
	public void initialize(ValidUserEmail constraintAnnotation) {
	}
	@Override
	
	public boolean isValid(UserDTO object, ConstraintValidatorContext context) {
		Long id = object.getId();
		String email = object.getEmail();
		boolean dataFound = false;
		if(null==id) {
			 dataFound = service.existsByEmail(email);
 
		}
		else {
			dataFound = service.existsByEmailAndIdNot(email,id);
		}
    	boolean isValid = !dataFound	;
        return isValid;
    }
 
	
	 
}
