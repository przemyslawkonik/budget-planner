package com.github.przemyslawkonik.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.bean.LoginData;
import com.github.przemyslawkonik.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("loginData", new LoginData());
		return "login";
	}

	@PostMapping("")
	public String login(Model model, @ModelAttribute LoginData loginData) {
		if (!userService.logIn(loginData)) {
			model.addAttribute("errorData", "Invalid data");
			return "login";
		}
		return "redirect:/";
	}

}
