package com.xyz.mydiary.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.mydiary.model.Diary;
import com.xyz.mydiary.model.UserDAO;
 
  
@Repository

public interface DiaryRepository extends JpaRepository<Diary, Long>{
 	
	List <Diary> findByUser(UserDAO user);

}
