package com.codingshuttle.ecommerce.order_Service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.codingshuttle.ecommerce.order_Service.entity.Orders;
import com.codingshuttle.ecommerce.order_Service.entity.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_Service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;
	
	public List<OrderRequestDto> getAllOrders(){
		log.info("Fetching  all orders");
		List<Orders> orders= orderRepository.findAll();
		return orders.stream()
				.map(order -> modelMapper.map(order,OrderRequestDto.class)).toList();
	}
	
	public OrderRequestDto getOrderById(Long id) {
		log.info("Fetching Oder By the Id: {} ",id);
		Orders orders = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order With Id Not Found"));
		return modelMapper.map(orders, OrderRequestDto.class);
	}
	
}
