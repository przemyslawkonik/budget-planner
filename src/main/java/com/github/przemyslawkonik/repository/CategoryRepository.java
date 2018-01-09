package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
