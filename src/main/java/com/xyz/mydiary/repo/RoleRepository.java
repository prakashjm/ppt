package com.xyz.mydiary.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.mydiary.model.Role;
@Repository

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
	
	List <Role> findBySuperRoleFalseAndStatus(String status);

}
