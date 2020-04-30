package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Transactions;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PolymorphicToolboxTransactionsTests {

	@Autowired
	private TransactionRepository tranRepository;

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
	void initTranRepo() {
		assertThat(tranRepository).isNotNull();
	}

	@Test
	void initTran() {
		assertThat(tranRepository.findById((long) 1).get()).isNotNull();
	}

	@Test
	void insertTran() {
		User user1 = new User("First", "Last", "email1@gmail.com", "pw", "user");
		userRepository.save(user1);

		Server server1 = new Server("Server1", "IP1", "username", "pw", "");
		serverRepository.save(server1);

		Server server2 = new Server("Server2", "IP2", "username", "pw", "");
		serverRepository.save(server2);

		Transactions tran1 = new Transactions(user1, server1, server2, "fileName", 1);
		tranRepository.save(tran1);

		assertThat(tranRepository.findAllByEmail("email1@gmail.com")).isNotNull();
	}

	@Test
	void insertTranList() {
		User user2 = new User("First", "Last", "email2@gmail.com", "pw", "user");
		userRepository.save(user2);

		Server server3 = new Server("Server3", "IP3", "username", "pw", "");
		serverRepository.save(server3);

		Server server4 = new Server("Server4", "IP4", "username", "pw", "");
		serverRepository.save(server4);

		Transactions tran2 = new Transactions(user2, server3, server4, "fileName", 1);
		tranRepository.save(tran2);

		assertEquals("IP3", tranRepository.findAllByEmail("email2@gmail.com").get(0).getSrc_server().getIp());
		assertEquals("IP4", tranRepository.findAllByEmail("email2@gmail.com").get(0).getDst_server().getIp());

		Transactions tran3 = new Transactions(user2, server4, server3, "fileName", 1);
		tranRepository.save(tran3);

		assertEquals("IP4", tranRepository.findAllByEmail("email2@gmail.com").get(1).getSrc_server().getIp());
		assertEquals("IP3", tranRepository.findAllByEmail("email2@gmail.com").get(1).getDst_server().getIp());
	}

	@Test
	void deleteTran() {
		User user3 = new User("First", "Last", "email3@gmail.com", "pw", "user");
		userRepository.save(user3);

		Server server5 = new Server("Server5", "IP5", "username", "pw", "");
		serverRepository.save(server5);

		Server server6 = new Server("Server6", "IP6", "username", "pw", "");
		serverRepository.save(server6);

		Transactions tran4 = new Transactions(user3, server5, server6, "fileName", 1);
		tranRepository.save(tran4);

		tranRepository.delete(tranRepository.findAllByEmail("email3@gmail.com").get(0));

		assertThat(tranRepository.findAllByEmail("email3@gmail.com")).isEmpty();
	}

	@Test
	void deleteUserWithTran() {
		User user4 = new User("First", "Last", "email4@gmail.com", "pw", "user");
		userRepository.save(user4);

		Server server7 = new Server("Server7", "IP7", "username", "pw", "");
		serverRepository.save(server7);

		Server server8 = new Server("Server8", "IP8", "username", "pw", "");
		serverRepository.save(server8);

		Transactions tran5 = new Transactions(user4, server7, server8, "fileName", 1);
		tranRepository.save(tran5);

		tranRepository.delete(tranRepository.findAllByEmail("email4@gmail.com").get(0));
		userRepository.delete(userRepository.findByEmail("email4@gmail.com"));

		assertThat(userRepository.findByEmail("email4@gmail.com")).isNull();
	}

	@Test
	void deleteServerWithTran() {
		User user5 = new User("First", "Last", "email5@gmail.com", "pw", "user");
		userRepository.save(user5);

		Server server9 = new Server("Server9", "IP9", "username", "pw", "");
		serverRepository.save(server9);

		Server server10 = new Server("Server10", "IP10", "username", "pw", "");
		serverRepository.save(server10);

		Transactions tran5 = new Transactions(user5, server9, server10, "fileName", 1);
		tranRepository.save(tran5);

		tranRepository.delete(tranRepository.findAllByEmail("email5@gmail.com").get(0));
		userRepository.delete(userRepository.findByEmail("email5@gmail.com"));

		assertThat(userRepository.findByEmail("email5@gmail.com")).isNull();
	}

}
