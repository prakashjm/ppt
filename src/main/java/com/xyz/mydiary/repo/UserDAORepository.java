package com.xyz.mydiary.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.mydiary.model.UserDAO;

@Repository

public interface UserDAORepository  extends JpaRepository<UserDAO, Long>{

	UserDAO findByEmail(String email);
	
	Page<UserDAO> findByEmailContainingIgnoreCaseAndIdNot(String email,Long id,Pageable pageable) ;
 
	
	Page<UserDAO> findByIdNot(Long id,Pageable pageable) ;
	
     
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot (String email,Long id);


}
 