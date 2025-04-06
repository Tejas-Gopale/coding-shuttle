package com.codingshuttle.ecommerce.order_Service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.ecommerce.order_Service.entity.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_Service.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

	private final OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
		log.info("Fetching all orders via controller");
		List<OrderRequestDto> orders= orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
		log.info("Fetching order with id :{} via controller");
		OrderRequestDto orders= orderService.getOrderById(id);
		return ResponseEntity.ok(orders);
	}
	
}
