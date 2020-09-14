package com.xyz.mydiary.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.xyz.mydiary.validation.UserDTO.ValidUserEmail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ValidUserEmail
public class UserDTO {

	private Long id;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email Mandatory")
	private String email;
	//@Pattern(regexp = "(?=.*[0-9])", message = "Password must contain one digit.")
	//@Pattern(regexp = "(?=.*[a-z])", message = "Password must contain one lowercase letter.")
	//@Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain one uppercase letter.")
	//@Pattern(regexp = "(?=\\S+$)", message = "Password must contain no whitespace.")
	
	@NotBlank(message = "Password Mandatory")
	private String password;

	private String userType;
 	
	@NotBlank(message = "First Name Mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid First Name")
	private String firstName;
 
	
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Middle Name")
	private String middleName;
	
	@NotBlank(message = "Last Name Mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Last Name")
	private String lastName;
	
	@NotBlank(message = "Mobile Number Mandatory")
 	@Pattern(regexp = "(\\+61|0)[0-9]{9}")


	private String mobile;


	private Boolean status;

	private String modifiedBy;

	private LocalDate accessDate;

	private Boolean locked;

	//private Set<Role> roles;


}