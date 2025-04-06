package com.codingshuttle.ecommerce.order_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingshuttle.ecommerce.order_Service.entity.Orders;

public interface OrderRepository extends  JpaRepository<Orders, Long>{

}
