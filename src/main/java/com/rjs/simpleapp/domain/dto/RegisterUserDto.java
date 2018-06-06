package com.rjs.simpleapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserDto {
	public final String username;
	public final String password;

	@JsonCreator
	public RegisterUserDto(@JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
	}
}
