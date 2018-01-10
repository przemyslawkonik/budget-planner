package com.github.przemyslawkonik.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "budget")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int month;

	private int year;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "budget", cascade = CascadeType.PERSIST)
	private Set<Plan> plans;

	// @OneToMany(fetch = FetchType.EAGER)
	// private Set<Plan> cashFlows;

	@ManyToOne
	private User user;

	public Budget() {
		plans = new HashSet<>();
		// cashFlows = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

	// public Set<Plan> getCashFlows() {
	// return cashFlows;
	// }
	//
	// public void setCashFlows(Set<Plan> cashFlows) {
	// this.cashFlows = cashFlows;
	// }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
