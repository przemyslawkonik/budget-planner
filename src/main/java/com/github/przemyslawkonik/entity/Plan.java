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
@Table(name = "plan")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private BigDecimal value;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Budget budget;

	public Plan() {
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

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public BigDecimal getReality() {
		BigDecimal sum = new BigDecimal("0");
		for (CashFlow cf : budget.getCashFlows()) {
			if (cf.getCategory().getName().equals(this.category.getName())) {
				sum = sum.add(cf.getValue());
			}
		}
		return sum.setScale(2, RoundingMode.DOWN);
	}

	public BigDecimal getBalance() {
		BigDecimal diff = new BigDecimal("0");
		if (category.getType().equals("income")) {
			diff = getReality().subtract(value);
			return diff.setScale(2, RoundingMode.DOWN);
		}
		diff = value.subtract(getReality());
		return diff.setScale(2, RoundingMode.DOWN);
	}

}
