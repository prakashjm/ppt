package com.xyz.mydiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.xyz.mydiary.model.UserDAO;
import com.xyz.mydiary.repo.UserDAORepository;

@Service
public class AccountUtilityService {

	
	@Autowired
	private UserDAORepository userDao;
 
	
public String getCurrUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}



public UserDAO getCurrUser() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String username = null;
	if (principal instanceof UserDetails) {
		username = ((UserDetails) principal).getUsername();
	} else {
		username = principal.toString();
	}
	return 	userDao.findByEmail(username);
}
 
}
