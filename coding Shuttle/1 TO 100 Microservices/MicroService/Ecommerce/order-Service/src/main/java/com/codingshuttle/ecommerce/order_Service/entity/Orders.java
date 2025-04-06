package com.codingshuttle.ecommerce.order_Service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private Double totalPrice;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items;
	
}
