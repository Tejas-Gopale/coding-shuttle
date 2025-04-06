package com.testing_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing_app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByEmail(String email);
}
