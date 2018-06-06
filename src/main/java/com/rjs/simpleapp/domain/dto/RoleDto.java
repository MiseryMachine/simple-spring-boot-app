package com.rjs.simpleapp.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {
	public final long id;
	public final String name;

	@JsonCreator
	public RoleDto(
			@JsonProperty("id") long id,
			@JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}
}
