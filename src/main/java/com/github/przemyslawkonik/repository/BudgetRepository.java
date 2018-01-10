package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.Budget;
import com.github.przemyslawkonik.entity.User;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

	public boolean existsByUserAndYearAndMonth(User user, int year, int month);
}
