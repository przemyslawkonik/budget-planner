package com.github.przemyslawkonik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.entity.CashFlow;
import com.github.przemyslawkonik.repository.CashFlowRepository;
import com.github.przemyslawkonik.repository.CategoryRepository;
import com.github.przemyslawkonik.repository.PaymentMethodRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/cash_flows")
public class CashFlowController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private PaymentMethodRepository paymentRepo;

	@Autowired
	private CashFlowRepository cashFlowRepo;

	@GetMapping("")
	public String cashFlows(Model model) {
		model.addAttribute("cashFlows", cashFlowRepo.findAllByUserId(userService.getSessionUser().getId()));
		return "/cashFlow/cash_flow_menu";
	}

	@GetMapping("/add")
	public String addCashFlow(Model model) {
		model.addAttribute("cashFlow", new CashFlow());
		model.addAttribute("categories", catRepo.findByUser(userService.getSessionUser()));
		model.addAttribute("methods", paymentRepo.findAllByUsers(userService.getSessionUser()));
		return "/cashFlow/cash_flow_add";
	}

	@PostMapping("/add")
	public String addCashFlow(@ModelAttribute CashFlow cashFlow) {
		cashFlowRepo.save(cashFlow);
		return "redirect:/cash_flows";
	}
}
