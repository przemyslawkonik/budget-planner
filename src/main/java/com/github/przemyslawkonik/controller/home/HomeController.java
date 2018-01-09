package com.github.przemyslawkonik.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("")
	public String home() {
		if (userService.isUserLogged()) {
			return "home/home";
		}
		return "redirect:/login";
	}
}
