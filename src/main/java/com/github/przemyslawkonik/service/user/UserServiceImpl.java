package com.github.przemyslawkonik.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.przemyslawkonik.bean.LoginData;
import com.github.przemyslawkonik.bean.SessionManager;
import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.exception.UserEmailException;
import com.github.przemyslawkonik.exception.UserLoginException;
import com.github.przemyslawkonik.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		if (!isEmailAvaliable(user.getEmail())) {
			throw new UserEmailException();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return logInUser(user);
	}

	@Override
	public User logInUser(User user) {
		SessionManager.session().setAttribute("user", user);
		return user;
	}

	@Override
	public User logInUser(LoginData loginData) {
		if (!verifyLogin(loginData)) {
			throw new UserLoginException();
		}
		return logInUser(userRepo.findByEmail(loginData.getEmail()));
	}

	@Override
	public void logOutUser() {
		SessionManager.session().invalidate();
	}

	@Override
	public boolean isUserLogged() {
		return SessionManager.session().getAttribute("user") != null;
	}

	@Override
	public User editUser(User user) {
		if (!isEmailAvaliable(user.getEmail())) {
			User dbUserId = userRepo.findOne(user.getId());
			User dbUserEmail = userRepo.findByEmail(user.getEmail());
			if (dbUserEmail.getId() != dbUserId.getId()) {
				throw new UserEmailException();
			}
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return logInUser(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepo.delete(id);
		logOutUser();
	}

	@Override
	public User getSessionUser() {
		return (User) SessionManager.session().getAttribute("user");
	}

	private boolean verifyLogin(LoginData ld) {
		User user = userRepo.findByEmail(ld.getEmail());
		return user != null && passwordEncoder.matches(ld.getPassword(), user.getPassword());
	}

	private boolean isEmailAvaliable(String email) {
		return !userRepo.existsByEmail(email);
	}

}
