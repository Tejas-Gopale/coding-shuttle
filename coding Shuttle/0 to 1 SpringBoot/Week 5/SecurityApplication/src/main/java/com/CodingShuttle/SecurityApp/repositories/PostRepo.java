package com.CodingShuttle.SecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingShuttle.SecurityApp.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long>{

}
