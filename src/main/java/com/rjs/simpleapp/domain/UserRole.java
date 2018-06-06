package com.rjs.simpleapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
	private Long id;
	private String name;

	public UserRole() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false, length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
