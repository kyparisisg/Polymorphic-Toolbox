package com.temple.polymorphic.toolbox;

import com.temple.polymorphic.toolbox.models.BucketCred;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PolymorphicToolboxBucketTests {

	@Autowired
	private BucketCredRepository bucRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testFail(){
//		assertTrue(false);
//	}

	@Test
	void initBucketRepo() {
		assertThat(bucRepository).isNotNull();
	}

	@Test
	void initBucket() {
		assertThat(bucRepository.findById((long) 1).get()).isNotNull();
	}

	@Test
	void insertBucket() {
		BucketCred buc1 = new BucketCred("bucketName1", "privateKey1", "publicKey1");
		bucRepository.save(buc1);
		assertThat(bucRepository.findByBucketName("bucketName1")).isNotNull();
	}

	@Test
	void deleteBucket() {
		BucketCred buc2 = new BucketCred("bucketName2", "privateKey2", "publicKey2");
		bucRepository.save(buc2);
		bucRepository.delete(bucRepository.findByBucketName("bucketName2"));
		assertThat(bucRepository.findByBucketName("bucketName2")).isNull();
	}
}
