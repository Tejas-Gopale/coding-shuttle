package com.codingshuttle.aopApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingshuttle.aopApp.serviceImpl.ShipmentServiceImpl;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class AopAppApplicationTests {

	@Autowired
	private ShipmentServiceImpl shipmentServiceImpl;
	
	@Test
	void aopTestOrderPackage() {
	 String orderString = shipmentServiceImpl.orderPackage(4L);
	 log.info(orderString);	
	}
	
	@Test
    void aopTestTrackPackage() {
        shipmentServiceImpl.trackPackage(4L);
    }

	
}
