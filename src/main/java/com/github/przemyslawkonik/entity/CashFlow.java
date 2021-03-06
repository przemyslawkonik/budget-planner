package com.github.przemyslawkonik.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cash_flow")
public class CashFlow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal value;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	private PaymentMethod paymentMethod;

	@ManyToOne(fetch = FetchType.EAGER)
	private Budget budget;

	public CashFlow() {
		value = new BigDecimal("0");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value.setScale(2, RoundingMode.DOWN);
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

}
