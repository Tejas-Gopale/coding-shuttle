package com.CodingShuttle.SecurityApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingShuttle.SecurityApp.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
