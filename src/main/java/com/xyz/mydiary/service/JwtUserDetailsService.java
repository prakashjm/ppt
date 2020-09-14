package com.xyz.mydiary.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.xyz.mydiary.dto.UserDTO;
import com.xyz.mydiary.model.Privilege;
import com.xyz.mydiary.model.Role;
import com.xyz.mydiary.model.UserDAO;
import com.xyz.mydiary.repo.UserDAORepository;
import com.xyz.mydiary.repo.VerificationTokenRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAORepository userDao;
	@Autowired
	private RoleService roleservice;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	AccountUtilityService accountUtilityService;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final UserDAO user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}

		Preconditions.checkNotNull(email);

		final Set<Role> rolesOfUser = user.getRoles();
		final Set<Privilege> privileges = Sets.newHashSet();
		for (final Role roleOfUser : rolesOfUser) {
			privileges.addAll(roleOfUser.getPrivileges());
		}
		final Function<Object, String> toStringFunction = Functions.toStringFunction();
		final Collection<String> rolesToString = Collections2.transform(privileges, toStringFunction);
		final String[] roleStringsAsArray = rolesToString.toArray(new String[rolesToString.size()]);
		final List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(roleStringsAsArray);

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auths);
	}

	UserDTO covertUserDaoToDTO(UserDAO source) {

		return modelMapper.map(source, UserDTO.class);

	}

	public UserDAO covertUserDTOToDAO(UserDTO source) {

		return modelMapper.map(source, UserDAO.class);

	}

	public UserDTO saveUser(UserDTO user) {
		user.setModifiedBy(accountUtilityService.getCurrUserName());
		return saveUser(covertUserDTOToDAO(user));

	}

	public UserDTO saveUser(@Valid UserDAO user) {
		return covertUserDaoToDTO(userDao.save(user));

	}

	public UserDTO save(UserDTO user) {
		UserDAO newUser = new UserDAO();
		newUser.setEmail(user.getEmail());
		System.out.println(newUser.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setStatus(true);
		newUser.setModifiedBy("sysadmin@bookapi.com");
		newUser.setFirstName(user.getFirstName());
		newUser.setMiddleName(user.getMiddleName());
		newUser.setLastName(user.getLastName());
		newUser.setMobile(user.getMobile());
		newUser.setUserType("UA");
		Role role = new Role();
		role = roleservice.findById(3L);
		Set<Role> roles = new HashSet();
		roles.add(role);
		newUser.setRoles(roles);
		newUser = userDao.save(newUser);

		user = covertUserDaoToDTO(newUser);
		return user;
	}

	public Boolean findByUserName(String name) {
		UserDAO user = userDao.findByEmail(name);
		if (null == user) {
			return false;
		} else {
			return true;
		}
	}

    
}