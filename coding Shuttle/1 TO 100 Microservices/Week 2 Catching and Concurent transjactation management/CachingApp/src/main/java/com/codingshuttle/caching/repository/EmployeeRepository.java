package com.codingshuttle.caching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingshuttle.caching.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	  List<Employee> findByEmail(String email);
}
