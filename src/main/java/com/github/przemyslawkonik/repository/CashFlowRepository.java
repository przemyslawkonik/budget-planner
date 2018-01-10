package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.CashFlow;

public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

}
