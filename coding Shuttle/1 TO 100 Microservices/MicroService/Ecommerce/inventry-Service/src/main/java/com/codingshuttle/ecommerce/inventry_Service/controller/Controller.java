package com.codingshuttle.ecommerce.inventry_Service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingshuttle.ecommerce.inventry_Service.dto.ProductDto;
import com.codingshuttle.ecommerce.inventry_Service.service.ProductService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class Controller {

	private final ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllInventory(){
		List<ProductDto> inventories = productService.getAllInventory();
		return ResponseEntity.ok(inventories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getinventoryById(@PathVariable Long id){
		ProductDto inventory = productService.getProductById(id);
		return ResponseEntity.ok(inventory);
	}
}
