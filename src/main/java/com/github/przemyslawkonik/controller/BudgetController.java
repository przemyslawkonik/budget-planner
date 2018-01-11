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
		Budget budget = budgetRepo.findLatestByUserId(userService.getSessionUser().getId());
		Budget newBudget = new Budget();
		if (budget == null) {
			newBudget.setYear(time.getYear());
			newBudget.setMonth(time.getMonth());
		} else {
			int nextMonth = time.getNextMonth(budget.getMonth());
			int nextYear = time.getNextYear(budget.getYear(), budget.getMonth());
			newBudget.setYear(nextYear);
			newBudget.setMonth(nextMonth);
		}
		newBudget.setUser(userService.getSessionUser());
		SessionManager.session().setAttribute("budget", newBudget);
		return "redirect:/budgets/add";
	}

	@GetMapping("/add")
	public String createBudget() {
		return "budget/budget_add";
	}

	@PostMapping("/add")
	public String saveBudget() {
		Budget budget = (Budget) SessionManager.session().getAttribute("budget");
		budgetRepo.save(budget);
		return "redirect:/";
	}
}
