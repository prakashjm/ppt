package com.xyz.mydiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.mydiary.model.Role;

@Service

public class RoleService {

	
	@Autowired
	private com.xyz.mydiary.repo.RoleRepository repo;
	
	
	public Role findByName(final String name) {
        return repo.findByName(name);
    }

	
	public List<Role> findActive(){
		return repo.findBySuperRoleFalseAndStatus("A");
	}
	public Role findById(Long id) {
		return repo.getOne(id);
	}
	
}
