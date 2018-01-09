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
import com.github.przemyslawkonik.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("")
	public String register(Model m, @Valid @ModelAttribute User user, BindingResult br) {
		if (br.hasErrors()) {
			return "register";
		}
		if (!userService.isEmailAvaliable(user.getEmail())) {
			m.addAttribute("errorEmail", "That email is already taken");
			return "register";
		}
		userService.save(user);
		return "redirect:/";
	}
}
