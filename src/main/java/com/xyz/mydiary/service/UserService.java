package com.xyz.mydiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.mydiary.model.UserDAO;
import com.xyz.mydiary.model.VerificationToken;
import com.xyz.mydiary.repo.UserDAORepository;
import com.xyz.mydiary.repo.VerificationTokenRepository;

@Service

public class UserService {
	@Autowired
	private UserDAORepository repo;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private VerificationTokenRepository tokenRepository;
	@Transactional(readOnly = true)
	public UserDAO findByName(final String name) {
		return repo.findByEmail(name);
	}

	public UserDAO saveUser(UserDAO user) {
		if(null==user.getId()) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		else {
			if(repo.getOne(user.getId()).getPassword()== user.getPassword()) {
				user.setPassword(repo.getOne(user.getId()).getPassword());	
			}
			else{
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		}
		
		System.out.println("xxxx"+user.getStatus());
		
		return repo.save(user);
	}
	
	public Page<UserDAO> findAll(Pageable pageable) {
 		return repo.findByIdNot(1L,pageable);
	}
	
	public Page<UserDAO> findAll(String userName,Pageable pageable) {
 		return repo.findByEmailContainingIgnoreCaseAndIdNot(userName,1L,pageable);
	}
	
	
	public UserDAO findById(Long id) {
		return repo.findById(id).get();
	}
	
	public UserDAO findByUserName(String userName) {
		return repo.findByEmail(userName);
	}
	public UserDAO passSave(String userName, String password) {
		UserDAO object = repo.findByEmail(userName);
		String encPass = passwordEncoder.encode(password);
		object.setPassword(encPass);
		return repo.save(object);


		
	}
	
	public     boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}
	
	public  boolean existsByEmailAndIdNot(String email,Long id) {
		
		return repo.existsByEmailAndIdNot(email, id);
	}
		
	public void createVerificationToken(UserDAO user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
	
	public UserDAO getUser(String verificationToken) {
		UserDAO user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

}
