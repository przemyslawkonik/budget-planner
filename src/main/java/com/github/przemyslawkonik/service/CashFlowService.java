package com.github.przemyslawkonik.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.przemyslawkonik.entity.CashFlow;
import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.repository.BudgetRepository;
import com.github.przemyslawkonik.repository.CashFlowRepository;
import com.github.przemyslawkonik.repository.UserRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Service
@Transactional
public class CashFlowService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CashFlowRepository cashFlowRepo;

	@Autowired
	private BudgetRepository budgetRepo;

	public void addOrEditCashFlow(CashFlow cashFlow) {
		User user = userRepo.findOne(userService.getSessionUser().getId());
		user.updateMoney(cashFlow);
		userRepo.save(user);
		cashFlow.setBudget(budgetRepo.findLatestByUserId(userService.getSessionUser().getId()));
		cashFlowRepo.save(cashFlow);
	}

	public void deleteCashFlow(long id) {
		CashFlow cashFlow = cashFlowRepo.findOne(id);
		User user = userRepo.findOne(userService.getSessionUser().getId());
		user.updateMoneyOnDelete(cashFlow);
		userRepo.save(user);
		cashFlowRepo.delete(cashFlow);
	}
}
