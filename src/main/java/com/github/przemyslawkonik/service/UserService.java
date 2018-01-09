package com.github.przemyslawkonik.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRep;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRep.save(user);
	}

}
