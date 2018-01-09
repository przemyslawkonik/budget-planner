package com.github.przemyslawkonik.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.exception.UserRegistrationException;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@PostMapping("")
	public String register(Model model, @Valid @ModelAttribute User user, BindingResult br) {
		if (br.hasErrors()) {
			return "user/register";
		}
		try {
			userService.registerUser(user);
			return "redirect:/";
		} catch (UserRegistrationException e) {
			model.addAttribute("errorEmail", "That email is already taken");
			return "user/register";
		}
	}
}
