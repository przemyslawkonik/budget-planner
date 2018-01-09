package com.github.przemyslawkonik.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.repository.UserRepository;

public class UserConverter implements Converter<String, User> {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User convert(String source) {
		return userRepo.findOne(Long.parseLong(source));
	}

}
