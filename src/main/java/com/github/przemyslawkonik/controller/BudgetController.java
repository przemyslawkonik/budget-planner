package com.github.przemyslawkonik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.przemyslawkonik.bean.SessionManager;
import com.github.przemyslawkonik.bean.Time;
import com.github.przemyslawkonik.entity.Budget;
import com.github.przemyslawkonik.repository.BudgetRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

	@Autowired
	private BudgetRepository budgetRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private Time time;

	@GetMapping("")
	public String budgets(Model model) {
		model.addAttribute("budget", budgetRepo.findLatestByUserId(userService.getSessionUser().getId()));
		return "budget/budget_menu";
	}

	@PostMapping("")
	public String budgets(Model model, @RequestParam(defaultValue = "0") int year,
			@RequestParam(defaultValue = "0") int month) {
		model.addAttribute("budget", budgetRepo.findByUserAndYearAndMonth(userService.getSessionUser(), year, month));
		return "budget/budget_menu";
	}

	@GetMapping("/new")
	public String newBudget() {
		if (!budgetRepo.existsByUserAndYearAndMonth(userService.getSessionUser(), time.getYear(), time.getMonth())) {
			SessionManager.session().setAttribute("budget", new Budget());
			return "redirect:/budgets/add";
		}
		return "redirect:/budgets";
	}

	@GetMapping("/add")
	public String createBudget(Model model) {
		Budget budget = (Budget) SessionManager.session().getAttribute("budget");
		budget.setYear(time.getYear());
		budget.setMonth(time.getMonth());
		budget.setUser(userService.getSessionUser());
		model.addAttribute("budget", budget);
		return "budget/budget_add";
	}

	@PostMapping("/add")
	public String createBudget() {
		Budget budget = (Budget) SessionManager.session().getAttribute("budget");
		budgetRepo.save(budget);
		return "redirect:/";
	}
}
