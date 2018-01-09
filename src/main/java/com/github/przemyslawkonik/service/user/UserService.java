package com.github.przemyslawkonik.service.user;

import com.github.przemyslawkonik.bean.LoginData;
import com.github.przemyslawkonik.entity.User;

public interface UserService {
	User registerUser(User user);

	User logInUser(LoginData loginData);

	User logInUser(User user);

	void logOutUser();

	boolean isUserLogged();

	User editUser(User user);

	void deleteUser(long id);

	User getSessionUser();
}
