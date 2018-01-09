package com.github.przemyslawkonik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.bean.SessionManager;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("")
	public String home() {
		if (SessionManager.session().getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "home";
	}
}
