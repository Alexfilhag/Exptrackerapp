package com.example.exptracker;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.exptracker.model.User;
import com.example.exptracker.model.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setUsername("Alex");
		user.setEmail("alex@gmai.com");
		user.setPasswordHash("alexalex");
		
		urepository.save(user);
		User savedUser = urepository.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}

	
}
