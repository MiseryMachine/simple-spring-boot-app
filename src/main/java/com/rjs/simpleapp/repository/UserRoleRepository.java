package com.rjs.simpleapp.repository;

import com.rjs.simpleapp.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByName(String name);
}
