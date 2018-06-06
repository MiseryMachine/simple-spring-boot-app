package com.rjs.simpleapp.repository;

import com.rjs.simpleapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	List<User> findByRoles_Name(String name);
}
