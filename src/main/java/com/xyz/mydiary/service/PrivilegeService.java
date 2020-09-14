package com.xyz.mydiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.mydiary.model.Privilege;
import com.xyz.mydiary.repo.PrivilegeRepository;

@Service

public class PrivilegeService {
	@Autowired
    private PrivilegeRepository repo;
	
	
	public Privilege findByName(final String name) {
        return repo.findByName(name);
    }
}
