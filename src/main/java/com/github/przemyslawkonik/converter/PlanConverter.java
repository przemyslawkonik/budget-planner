package com.github.przemyslawkonik.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.github.przemyslawkonik.entity.Plan;
import com.github.przemyslawkonik.repository.PlanRepository;

public class PlanConverter implements Converter<String, Plan> {
	@Autowired
	private PlanRepository planRepo;

	@Override
	public Plan convert(String source) {
		return planRepo.findOne(Long.parseLong((source)));
	}

}
