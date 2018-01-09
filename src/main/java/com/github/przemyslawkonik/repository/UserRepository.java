package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public boolean existsByEmail(String email);
}
