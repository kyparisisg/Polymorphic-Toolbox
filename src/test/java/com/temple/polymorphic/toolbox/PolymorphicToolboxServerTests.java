package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.controllers.ServersController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PolymorphicToolboxServerTests {

	@Autowired
	private ServerRepository serverRepository;

	@Autowired
	private ServersController serverController;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testFail(){
//		assertTrue(false);
//	}

	//------------------------------------------------------------------------------------------------------------------
	// --- REPOSITORY TESTS ---
	//------------------------------------------------------------------------------------------------------------------

	@Test
	void initServerRepo(){
		assertThat(serverRepository).isNotNull();
	}

	@Test
	void initServers() {
		Server server1 = serverRepository.findById((long) 1).get();
		assertThat(server1).isNotNull();
	}

	@Test
	void insertServer(){
		Server server1 = new Server("Server1", "IP1", "username", "pw", "");
		serverRepository.save(server1);
		assertThat(serverRepository.findByIp("IP1")).isNotNull();
	}


	@Test
	void deleteServer(){
		Server server2 = new Server("Server2", "IP2", "username", "pw", "");
		serverRepository.save(server2);
		serverRepository.delete(serverRepository.findByIp("IP2"));
		assertThat(serverRepository.findByIp("IP2")).isNull();
	}

	//------------------------------------------------------------------------------------------------------------------
	// --- CONTROLLER TESTS ---
	//------------------------------------------------------------------------------------------------------------------

	@Test
	void initServerController() {
		assertThat(serverController).isNotNull();
	}

}
