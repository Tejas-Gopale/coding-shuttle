package com.codingshuttle.ecommerce.inventry_Service.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.codingshuttle.ecommerce.inventry_Service.dto.ProductDto;
import com.codingshuttle.ecommerce.inventry_Service.entity.ProductEntity;
import com.codingshuttle.ecommerce.inventry_Service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	
	private final ModelMapper modelMapper;
	private final ProductRepository productRepository;
	
	public List<ProductDto> getAllInventory(){
		log.info("Fetching all inventory items");
		List<ProductEntity> inventories = productRepository.findAll();
		return inventories.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.toList();
	}
	
	public ProductDto getProductById( Long id) {
		log.info("Fetching Product By Id: {}",id);
		Optional<ProductEntity> inventory = productRepository.findById(id);
		return inventory
				.map(item -> modelMapper
						.map(item, ProductDto.class))
				.orElseThrow(
						()-> new RuntimeException("Inventory with id "+id +" Not found." ));
	}
	
}
