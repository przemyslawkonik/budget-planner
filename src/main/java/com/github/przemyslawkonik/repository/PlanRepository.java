package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
