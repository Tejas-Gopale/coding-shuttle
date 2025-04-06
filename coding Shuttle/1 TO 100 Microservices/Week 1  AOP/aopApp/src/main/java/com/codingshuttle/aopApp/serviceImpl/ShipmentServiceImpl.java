package com.codingshuttle.aopApp.serviceImpl;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingshuttle.aopApp.aspect.MyLoggingAnnotation;
import com.codingshuttle.aopApp.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShipmentServiceImpl implements ShipmentService{

	@Override
	@Transactional
	@MyLoggingAnnotation
	public String orderPackage(Long orderId) {
	try {
		log.info("Processing the order ......");
		Thread.sleep(1000);
	}catch (Exception e) {
		log.error("Error occured while processing your Order",e);
	}
		return "Order has processed successfully, orderId: "+orderId;
	}

	@Override
	@Transactional
	public String trackPackage(Long orderId) {
		try {
			log.info("Tracking the order....");
			Thread.sleep(500);
			throw new RuntimeException("Excetion Occured During trackPackage ...");
		}catch (Exception e) {
		throw new RuntimeException(e);
		}
	}

	//weaving - if we use aspect j . actualy changes the bytecode.
	//spring apo is diffrent 
}
