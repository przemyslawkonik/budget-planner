package com.github.przemyslawkonik.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.bean.SessionManager;
import com.github.przemyslawkonik.entity.Budget;
import com.github.przemyslawkonik.entity.Plan;
import com.github.przemyslawkonik.repository.CategoryRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/plans")
public class PlanController {

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private UserService userService;

	@GetMapping("/add")
	public String addPlan(Model model) {
		model.addAttribute("plan", new Plan());
		model.addAttribute("categories", catRepo.findByUser(userService.getSessionUser()));
		return "/plan/plan_add";
	}

	@PostMapping("/add")
	public String addPlan(@ModelAttribute Plan plan) {
		Budget budget = (Budget) SessionManager.session().getAttribute("budget");
		Set<Plan> plans = budget.getPlans();
		plan.setBudget(budget);
		plans.add(plan);
		budget.setPlans(plans);
		return "redirect:/budgets/add";
	}
}
