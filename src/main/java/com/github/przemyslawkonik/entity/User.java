package com.github.przemyslawkonik.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Email
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotBlank
	@Size(min = 4, max = 24)
	private String username;

	@NotBlank
	@Size(min = 5, max = 60)
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
	private Set<Category> categories;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "payment_method_id") })
	private Set<PaymentMethod> paymentMethods;

	private BigDecimal cash;

	private BigDecimal accountValue;

	public User() {
		paymentMethods = new HashSet<>();
		cash = new BigDecimal("0");
		accountValue = new BigDecimal("0");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public BigDecimal getCash() {
		return cash.setScale(2, RoundingMode.DOWN);
	}

	public void setCash(BigDecimal amount) {
		this.cash = amount;
	}

	public BigDecimal getAccountValue() {
		return accountValue.setScale(2, RoundingMode.DOWN);
	}

	public void setAccountValue(BigDecimal amount) {
		this.accountValue = amount;
	}

	public BigDecimal getBalance() {
		return getCash().add(getAccountValue());
	}

	public BigDecimal addCash(BigDecimal amount) {
		return cash = getCash().add(amount);
	}

	public BigDecimal addToAccount(BigDecimal amount) {
		return accountValue = getAccountValue().add(amount);
	}

	public BigDecimal subtractCash(BigDecimal amount) {
		return cash = getCash().subtract(amount);
	}

	public BigDecimal subtractFromAccount(BigDecimal amount) {
		return accountValue = getAccountValue().subtract(amount);
	}

	public BigDecimal getMoney() {
		BigDecimal money = cash.add(accountValue);
		return money.setScale(2, RoundingMode.DOWN);
	}

	public void updateMoney(CashFlow cashFlow) {
		switch (cashFlow.getCategory().getType()) {
		case "income": {
			if (cashFlow.getPaymentMethod().getName().equals("cash")) {
				addCash(cashFlow.getValue());
			} else {
				addToAccount(cashFlow.getValue());
			}
			break;
		}
		case "expense": {
			if (cashFlow.getPaymentMethod().getName().equals("cash")) {
				subtractCash(cashFlow.getValue());
			} else {
				subtractFromAccount(cashFlow.getValue());
			}
			break;
		}
		}
	}

	public void updateMoneyOnDelete(CashFlow cashFlow) {
		switch (cashFlow.getCategory().getType()) {
		case "income": {
			if (cashFlow.getPaymentMethod().getName().equals("cash")) {
				subtractCash(cashFlow.getValue());
			} else {
				subtractFromAccount(cashFlow.getValue());
			}
			break;
		}
		case "expense": {
			if (cashFlow.getPaymentMethod().getName().equals("cash")) {
				addCash(cashFlow.getValue());
			} else {
				addToAccount(cashFlow.getValue());
			}
			break;
		}
		}
	}
}
