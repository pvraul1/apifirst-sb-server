package com.rperezv365.apifirst.apifirstserver;

import com.rperezv365.apifirst.apifirstserver.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApifirstServerApplicationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testDataLoad() {
		System.out.println("Number of customers: " + customerRepository.count());

		assertThat(customerRepository.count()).isGreaterThan(0L);
	}
}