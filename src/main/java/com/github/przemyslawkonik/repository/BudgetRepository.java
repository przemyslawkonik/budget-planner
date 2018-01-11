package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.przemyslawkonik.entity.Budget;
import com.github.przemyslawkonik.entity.User;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

	public boolean existsByUserAndYearAndMonth(User user, int year, int month);

	@Query(value = "SELECT * FROM budget ORDER BY id DESC LIMIT 1", nativeQuery = true)
	public Budget findLatest();

	public Budget findByYearAndMonth(int year, int month);
}
