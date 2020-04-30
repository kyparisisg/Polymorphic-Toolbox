package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.User;
import com.temple.polymorphic.toolbox.controllers.UsersController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PolymorphicToolboxUserTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UsersController userController;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testFail(){
//		assertTrue(false);
//	}

	@Test
	void initUserRepo() {
		assertThat(userRepository).isNotNull();
	}

	@Test
	void initUser() {
		assertThat(userRepository.findById((long) 1).get()).isNotNull();
	}

	@Test
	void insertUser() {
		User user1 = new User("First", "Last", "email1@gmail.com", "pw", "user");
		userRepository.save(user1);
		assertThat(userRepository.findByEmail("email1@gmail.com")).isNotNull();
	}

	@Test
	void updateUser() {
		User user2 = new User("First", "Last", "email2@gmail.com", "pw", "user");
		userRepository.save(user2);
		userRepository.updateUserPassword("pw2", "email2@gmail.com");
		assertEquals("pw2", userRepository.findByEmail("email2@gmail.com").getPassword());
	}

	@Test
	void deleteUser() {
		User user3 = new User("First", "Last", "email3@gmail.com", "pw", "user");
		userRepository.save(user3);
		userRepository.delete(userRepository.findByEmail("email3@gmail.com"));
		assertThat(userRepository.findByEmail("email3@gmail.com")).isNull();
	}
}
