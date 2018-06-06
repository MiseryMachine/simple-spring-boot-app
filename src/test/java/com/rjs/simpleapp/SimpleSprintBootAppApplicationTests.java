package com.rjs.simpleapp;

import com.rjs.simpleapp.controller.UserController;
import com.rjs.simpleapp.domain.UserRole;
import com.rjs.simpleapp.domain.dto.RegisterUserDto;
import com.rjs.simpleapp.domain.dto.UserDto;
import com.rjs.simpleapp.service.UserRoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class SimpleSprintBootAppApplicationTests {
	@Autowired
	private UserController userController;
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void init() {
		UserRole userRole = new UserRole();
		userRole.setId(1L);
		userRole.setName("ROLE_USER");
		userRole = entityManager.persist(userRole);

		userRole = new UserRole();
		userRole.setId(2L);
		userRole.setName("ROLE_ADMIN");
		userRole = entityManager.persist(userRole);
		entityManager.flush();
	}

	@Test
	public void contextLoads() {
		assertThat(userController).isNotNull();
	}

	@Test
	public void createUser() {
		RegisterUserDto registerUserDto = new RegisterUserDto("rjs", "pass");

		ResponseEntity<UserDto> responseEntity = restTemplate.exchange(
				"http://localhost:8080/user/create",
				HttpMethod.POST,
				new HttpEntity<>(registerUserDto),
				UserDto.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		UserDto userDto = responseEntity.getBody();
		assertThat(userDto.id).isPositive();
	}
}
