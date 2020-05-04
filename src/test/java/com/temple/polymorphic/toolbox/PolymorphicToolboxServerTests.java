package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.Server;
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

	@Test
	void contextLoads() {
	}

//	@Test
//	void testFail(){
//		assertTrue(false);
//	}


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
	void checkServerHealth(){
		Server server2 = new Server("Server2", "IP2", "username", "pw", "");
		serverRepository.save(server2);
		serverRepository.updateServerHealth(1, serverRepository.findByIp("IP2").getId());
		assertEquals(1, serverRepository.findByIp("IP2").getHealth());
	}

	@Test
	void deleteServer(){
		Server server3 = new Server("Server3", "IP3", "username", "pw", "");
		serverRepository.save(server3);
		serverRepository.delete(serverRepository.findByIp("IP3"));
		assertThat(serverRepository.findByIp("IP3")).isNull();
	}
}
