package com.github.przemyslawkonik.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.przemyslawkonik.bean.LoginData;
import com.github.przemyslawkonik.bean.SessionManager;
import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean register(User user) {
		if (isEmailAvaliable(user.getEmail())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			SessionManager.session().setAttribute("user", user);
			return true;
		}
		return false;
	}

	public boolean logIn(LoginData loginData) {
		if (loginData.isUserValid()) {
			User user = loginData.getUser();
			SessionManager.session().setAttribute("user", user);
			return true;
		}
		return false;
	}

	private boolean isEmailAvaliable(String email) {
		return !userRepo.existsByEmail(email);
	}

}
