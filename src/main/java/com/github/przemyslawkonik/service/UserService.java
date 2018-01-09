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

	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return logIn(user);
	}

	public User logIn(User user) {
		SessionManager.session().setAttribute("user", user);
		return user;
	}

	public void logOut() {
		SessionManager.session().invalidate();
	}

	public boolean verifyLogin(LoginData ld) {
		User user = userRepo.findByEmail(ld.getEmail());
		return user != null && passwordEncoder.matches(ld.getPassword(), user.getPassword());
	}

	public boolean isEmailAvaliable(String email) {
		return !userRepo.existsByEmail(email);
	}

}
