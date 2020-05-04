package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Permissions;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PolymorphicToolboxPermissionsTests {

	@Autowired
	private PermissionRepository permRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServerRepository serverRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testFail(){
//		assertTrue(false);
//	}

	@Test
	void initPermRepo() {
		assertThat(permRepository).isNotNull();
	}

	@Test
	void initPerm() {
		assertThat(permRepository.findById((long) 1).get()).isNotNull();
	}

	@Test
	void insertPerm() {
		User user1 = new User("First", "Last", "email1@gmail.com", "pw", "user");
		userRepository.save(user1);

		Server server1 = new Server("Server1", "IP1", "username", "pw", "");
		serverRepository.save(server1);

		Permissions perm1 = new Permissions(user1, server1);
		permRepository.save(perm1);

		assertThat(permRepository.findByIds(userRepository.findByEmail("email1@gmail.com").getId(),
				serverRepository.findByIp("IP1").getId())).isNotNull();
	}

	@Test
	void insertPermWithSpecificCreds() {
		User user2 = new User("First", "Last", "email2@gmail.com", "pw", "user");
		userRepository.save(user2);

		Server server2 = new Server("Server2", "IP2", "username", "pw", "");
		serverRepository.save(server2);

		Permissions perm2 = new Permissions(user2, server2, "newUsername", "newPassword");
		permRepository.save(perm2);

		assertEquals("newUsername",permRepository.findByIds(userRepository.findByEmail("email2@gmail.com").getId(),
				serverRepository.findByIp("IP2").getId()).getUsernameCred());
		assertEquals("newPassword",permRepository.findByIds(userRepository.findByEmail("email2@gmail.com").getId(),
				serverRepository.findByIp("IP2").getId()).getPasswordCred());
	}

	@Test
	void insertPermList() {
		User user3 = new User("First", "Last", "email3@gmail.com", "pw", "user");
		userRepository.save(user3);

		Server server3 = new Server("Server3", "IP3", "username", "pw", "");
		serverRepository.save(server3);

		Server server4 = new Server("Server4", "IP4", "username", "pw", "");
		serverRepository.save(server4);

		Permissions perm3 = new Permissions(user3, server3);
		permRepository.save(perm3);

		Permissions perm4 = new Permissions(user3, server4);
		permRepository.save(perm4);

		assertEquals("IP3", permRepository.findAllByEmail("email3@gmail.com").get(0).getServer().getIp());
		assertEquals("IP4", permRepository.findAllByEmail("email3@gmail.com").get(1).getServer().getIp());
	}

	@Test
	void deletePerm() {
		User user4 = new User("First", "Last", "email4@gmail.com", "pw", "user");
		userRepository.save(user4);

		Server server5 = new Server("Server5", "IP5", "username", "pw", "");
		serverRepository.save(server5);

		Permissions perm4 = new Permissions(user4, server5);
		permRepository.save(perm4);

		permRepository.delete(permRepository.findByIds(userRepository.findByEmail("email4@gmail.com").getId(),
				serverRepository.findByIp("IP5").getId()));

		assertThat(permRepository.findByIds(userRepository.findByEmail("email4@gmail.com").getId(),
				serverRepository.findByIp("IP5").getId())).isNull();
	}

	@Test
	void deleteUserWithPerm() {
		User user5 = new User("First", "Last", "email5@gmail.com", "pw", "user");
		userRepository.save(user5);

		Server server6 = new Server("Server6", "IP6", "username", "pw", "");
		serverRepository.save(server6);

		Permissions perm5 = new Permissions(user5, server6);
		permRepository.save(perm5);

		permRepository.deleteByUser(userRepository.findByEmail("email5@gmail.com"));
		userRepository.delete(userRepository.findByEmail("email5@gmail.com"));

		assertThat(userRepository.findByEmail("email5@gmail.com")).isNull();

	}

	@Test
	void deleteServerWithPerm() {
		User user6 = new User("First", "Last", "email6@gmail.com", "pw", "user");
		userRepository.save(user6);

		Server server7 = new Server("Server7", "IP7", "username", "pw", "");
		serverRepository.save(server7);

		Permissions perm6 = new Permissions(user6, server7);
		permRepository.save(perm6);

		permRepository.deleteByServer(serverRepository.findByIp("IP7"));
		serverRepository.delete(serverRepository.findByIp("IP7"));

		assertThat(serverRepository.findByIp("IP7")).isNull();
	}

}
