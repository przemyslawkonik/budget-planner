package com.github.przemyslawkonik.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.repository.BudgetRepository;
import com.github.przemyslawkonik.repository.UserRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private BudgetRepository budgetRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String home(Model model) {
		if (userService.isUserLogged()) {
			model.addAttribute("budget", budgetRepo.findLatestByUserId(userService.getSessionUser().getId()));
			model.addAttribute("user", userRepo.findOne(userService.getSessionUser().getId()));
			return "home/home";
		}
		return "redirect:/login";
	}
}
