package com.github.przemyslawkonik.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.github.przemyslawkonik.entity.Budget;
import com.github.przemyslawkonik.repository.BudgetRepository;

public class BudgetConverter implements Converter<String, Budget> {
	@Autowired
	private BudgetRepository budgetRepo;

	@Override
	public Budget convert(String source) {
		return budgetRepo.findOne(Long.parseLong((source)));
	}

}
