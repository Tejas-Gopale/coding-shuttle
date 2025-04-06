package com.testing_app;

import org.assertj.core.api.Assert;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class TestingAppApplicationTests {

	@BeforeEach
	void setUp() {
		log.info("Starting the Method");
	}
	
	@AfterEach
	void tearDown() {
		log.info("Tearing Down");
	}
	
	
	
	@Test
	void contextLoads() {
		log.info("Context Text Method : Test no 1");
	}

	@Test
	@DisplayName("DisplayNameTwo")//changes the name of the Method
	void testNumberTwo() {
		int a =5 ;
		int b =3 ;
		int result = addTwoNumbers(a, b);
		
//		Assertions.assertEquals(result, 8);
//		Assertions.
		org.assertj.core.api.Assertions.assertThat(result)
				.isEqualTo(8)
				.isCloseTo(9, Offset.offset(1));
	}
	
	int addTwoNumbers(int a, int b) {
		return a+b;
	}
	
	
  
}
