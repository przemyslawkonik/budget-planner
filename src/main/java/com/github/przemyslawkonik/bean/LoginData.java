package com.github.przemyslawkonik.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.repository.UserRepository;

@Component
public class LoginData {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUserValid() {
		User user = userRepo.findByEmail(email);
		return user != null && passwordEncoder.matches(password, user.getPassword());
	}

	public User getUser() {
		return userRepo.findByEmail(email);
	}

}
