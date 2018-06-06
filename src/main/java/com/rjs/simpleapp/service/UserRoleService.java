package com.rjs.simpleapp.service;

import com.rjs.simpleapp.domain.UserRole;
import com.rjs.simpleapp.repository.UserRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userRoleService")
public class UserRoleService {
	private UserRoleRepository userRoleRepository;

	@Autowired
	public UserRoleService(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	public UserRole createUserRole(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("User role name parameter cannot be empty.");
		}

		UserRole userRole = new UserRole();
		userRole.setName(name);

		return userRoleRepository.save(userRole);
	}

	public UserRole getByName(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("User role name parameter cannot be empty.");
		}

		return userRoleRepository.findByName(name);
	}

	public UserRole getById(long id) {
		Optional<UserRole> userRoleOptional = userRoleRepository.findById(id);
		return userRoleOptional.orElse(null);
	}

	public UserRole updateUserRole(UserRole userRole) {
		if (userRole == null) {
			throw new IllegalArgumentException("User role parameter cannot be empty.");
		}

		return userRoleRepository.save(userRole);
	}

	public void removeUserRole(UserRole userRole) {
		if (userRole == null) {
			throw new IllegalArgumentException("User role parameter cannot be empty.");
		}

		userRoleRepository.delete(userRole);
	}

	public void removeUserRole(long userRoleId) {
		userRoleRepository.deleteById(userRoleId);
	}
}
