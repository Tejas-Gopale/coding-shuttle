package com.codingshuttle.ecommerce.order_Service.entity.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {
	private Long id;
	private List<OrderRequestItemDto> items;
	private BigDecimal totalPrice;
}	
