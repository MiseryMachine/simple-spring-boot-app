package com.rjs.simpleapp.controller;

import com.rjs.simpleapp.domain.User;
import com.rjs.simpleapp.domain.UserRole;
import com.rjs.simpleapp.domain.dto.RegisterUserDto;
import com.rjs.simpleapp.domain.dto.UserDto;
import com.rjs.simpleapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<UserDto> loginUser(String username) {
		try {
			UserDto userDto = convert(userService.getByUsername(username));

			return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.notFound().build();
		}
		catch (Exception e) {
			LOGGER.error("Error looking up user: " + username, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(RegisterUserDto registerUser) {
		try {
			User user = userService.createUser(registerUser.username, registerUser.password);
			return ResponseEntity.ok(convert(user));
		}
		catch (IllegalArgumentException e) {
			LOGGER.error("Error creating user: " + registerUser.username, e);
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
		catch (IllegalStateException e) {
			LOGGER.error("Error creating user: " + registerUser.username, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	private UserDto convert(User user) {
		return user != null ? new UserDto(user.getId() != null ? user.getId() : -1L, user.getUsername(), user.getFirstName() + " " + user.getLastName(),
				user.getRoles().stream().map(UserRole::getName).collect(Collectors.toList())) : null;
	}
}
