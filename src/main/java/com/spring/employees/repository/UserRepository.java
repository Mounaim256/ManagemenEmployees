package com.spring.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.spring.employees.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
