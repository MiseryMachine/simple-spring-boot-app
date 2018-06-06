package com.rjs.simpleapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDto {
	public final long id;
	public final String username;
	public final String fullName;
	public List<String> roles;

	@JsonCreator
	public UserDto(
			@JsonProperty("id") long id,
			@JsonProperty("username") String username,
			@JsonProperty("full-name") String fullName,
			@JsonProperty("roles") List<String> roles) {
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.roles = roles;
	}
}
