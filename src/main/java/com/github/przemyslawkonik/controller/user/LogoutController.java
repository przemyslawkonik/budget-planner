package com.github.przemyslawkonik.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.service.UserService;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String logout() {
		userService.logOut();
		return "redirect:/";
	}

}
