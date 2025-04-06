package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long>{
//	List<EmployeeEntity> FindByUsername();
}
	