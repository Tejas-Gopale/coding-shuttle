package com.codingshuttle.ecommerce.order_Service.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestItemDto {

	private Long id;
	private Long productId;
	private Integer quantity;
}
