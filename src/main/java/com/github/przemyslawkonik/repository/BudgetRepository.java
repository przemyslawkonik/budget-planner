package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

}
