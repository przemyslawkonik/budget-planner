package com.github.przemyslawkonik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.entity.CashFlow;
import com.github.przemyslawkonik.entity.Category;
import com.github.przemyslawkonik.entity.PaymentMethod;
import com.github.przemyslawkonik.entity.Plan;
import com.github.przemyslawkonik.repository.BudgetRepository;
import com.github.przemyslawkonik.repository.CashFlowRepository;
import com.github.przemyslawkonik.repository.PaymentMethodRepository;
import com.github.przemyslawkonik.service.CashFlowService;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/cash_flows")
public class CashFlowController {

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentMethodRepository paymentRepo;

	@Autowired
	private CashFlowRepository cashFlowRepo;

	@Autowired
	private BudgetRepository budgetRepo;

	@Autowired
	private CashFlowService cashFlowService;

	@GetMapping("")
	public String cashFlows(Model model) {
		model.addAttribute("cashFlows", cashFlowRepo.findAllByUserId(userService.getSessionUser().getId()));
		return "/cashFlow/cash_flow_menu";
	}

	@GetMapping("/add")
	public String addCashFlow(Model model) {
		model.addAttribute("cashFlow", new CashFlow());
		return "/cashFlow/cash_flow_add";
	}

	@PostMapping("/add")
	public String addCashFlow(@ModelAttribute CashFlow cashFlow) {
		cashFlowService.addOrEditCashFlow(cashFlow);
		return "redirect:/cash_flows";
	}

	@GetMapping("/edit/{id}")
	public String editCashFlow(Model model, @PathVariable long id) {
		model.addAttribute("cashFlow", cashFlowRepo.findOne(id));
		return "/cashFlow/cash_flow_edit";
	}

	@PostMapping("/edit/{id}")
	public String editCashFlow(@ModelAttribute CashFlow cashFlow) {
		cashFlowService.addOrEditCashFlow(cashFlow);
		return "redirect:/cash_flows";
	}

	@GetMapping("/remove/{id}")
	public String removeCashFlow(@PathVariable long id) {
		cashFlowService.deleteCashFlow(id);
		return "redirect:/cash_flows";
	}

	@ModelAttribute("categories")
	public List<Category> categories() {
		List<Category> categories = new ArrayList<>();
		for (Plan p : budgetRepo.findLatestByUserId(userService.getSessionUser().getId()).getPlans()) {
			categories.add(p.getCategory());
		}
		return categories;
	}

	@ModelAttribute("methods")
	public List<PaymentMethod> methods() {
		return paymentRepo.findAllByUsers(userService.getSessionUser());
	}

}
