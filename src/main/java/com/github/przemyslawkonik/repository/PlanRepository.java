package com.github.przemyslawkonik.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.Budget;
import com.github.przemyslawkonik.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

	public Set<Plan> findByBudget(Budget budget);

}
