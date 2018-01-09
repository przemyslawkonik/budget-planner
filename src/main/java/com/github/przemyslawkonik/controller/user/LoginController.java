package com.github.przemyslawkonik.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.bean.LoginData;
import com.github.przemyslawkonik.exception.UserLoginException;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("loginData", new LoginData());
		return "user/login";
	}

	@PostMapping("")
	public String login(Model model, @ModelAttribute LoginData loginData) {
		try {
			userService.logInUser(loginData);
			return "redirect:/";
		} catch (UserLoginException e) {
			model.addAttribute("errorData", "Incorrect data");
			return "user/login";
		}
	}

}
