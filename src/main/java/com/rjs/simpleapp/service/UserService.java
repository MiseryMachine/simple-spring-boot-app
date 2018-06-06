package com.rjs.simpleapp.service;

import com.rjs.simpleapp.domain.User;
import com.rjs.simpleapp.domain.UserRole;
import com.rjs.simpleapp.repository.UserRepository;
import com.rjs.simpleapp.repository.UserRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	public User createUser(String username, String password) throws IllegalStateException {
		if (userRepository.findByUsername(username) != null) {
			throw new IllegalArgumentException("User with username already exists.");
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		// Get basic "USER" role
		UserRole userRole = userRoleRepository.findByName("ROLE_USER");

		if (userRole == null) {
			throw new IllegalStateException("No default user role configured.");
		}

		user.getRoles().add(userRole);

		// Persist the entity
		user = userRepository.save(user);

		return user;
	}

	public User getByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new IllegalArgumentException("Username parameter cannot be empty.");
		}

		return userRepository.findByUsername(username);
	}

	public User updateUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User parameter cannot be empty.");
		}

		return userRepository.save(user);
	}

	public void removeUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("User parameter cannot be empty.");
		}

		userRepository.delete(user);
	}

	public void removeUser(long userId) {
		userRepository.deleteById(userId);
	}
}
