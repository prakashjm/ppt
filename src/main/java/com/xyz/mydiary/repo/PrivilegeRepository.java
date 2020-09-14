package com.xyz.mydiary.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.mydiary.model.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
	Privilege findByName(String name);

}
