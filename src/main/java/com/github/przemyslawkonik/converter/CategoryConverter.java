package com.github.przemyslawkonik.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.github.przemyslawkonik.entity.Category;
import com.github.przemyslawkonik.repository.CategoryRepository;

public class CategoryConverter implements Converter<String, Category> {
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public Category convert(String source) {
		return catRepo.findOne(Long.parseLong((source)));
	}

}
