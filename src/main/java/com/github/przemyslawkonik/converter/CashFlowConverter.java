package com.github.przemyslawkonik.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.github.przemyslawkonik.entity.CashFlow;
import com.github.przemyslawkonik.repository.CashFlowRepository;

public class CashFlowConverter implements Converter<String, CashFlow> {
	@Autowired
	private CashFlowRepository cashFlowRepo;

	@Override
	public CashFlow convert(String source) {
		return cashFlowRepo.findOne(Long.parseLong((source)));
	}

}
