package com.github.przemyslawkonik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.Category;
import com.github.przemyslawkonik.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByUserId(long id);

	List<Category> findByUser(User user);
}
