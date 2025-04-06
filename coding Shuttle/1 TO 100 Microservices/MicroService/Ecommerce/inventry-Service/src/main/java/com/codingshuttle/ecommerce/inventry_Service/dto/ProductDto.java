package com.codingshuttle.ecommerce.inventry_Service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
	private Long id;
	private String title;
	private Double price;
	private Integer stock; 	
}
