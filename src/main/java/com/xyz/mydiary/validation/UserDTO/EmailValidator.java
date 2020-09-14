package com.xyz.mydiary.validation.UserDTO;

 
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
 
public class EmailValidator 
implements ConstraintValidator<ValidEmail,Object > {
  
  private Pattern pattern;
  private java.util.regex.Matcher matcher;
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+ (.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$"; 
  @Override
  public void initialize(ValidEmail constraintAnnotation) {       
  }
@Override
public boolean isValid(Object value, ConstraintValidatorContext context) {
	// TODO Auto-generated method stub
	System.out.println("sssssssssssssssss");
	return false;
}
 
  private boolean validateEmail(String email) {
     // pattern = Pattern.compile(EMAIL_PATTERN);
      //matcher = pattern.matcher(email);
	  System.out.println(email);
      return true;
  }
}

