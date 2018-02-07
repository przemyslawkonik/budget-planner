package com.github.przemyslawkonik.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	@Size(min = 3, max = 20)
	@Column(unique = true)
	private String name;

	@ManyToMany(mappedBy = "paymentMethods", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private Set<User> users;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "paymentMethod", cascade = { CascadeType.MERGE })
	private Set<CashFlow> cashFlows;

	public PaymentMethod() {
		users = new HashSet<>();
		cashFlows = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<CashFlow> getCashFlows() {
		return cashFlows;
	}

	public void setCashFlows(Set<CashFlow> cashFlows) {
		this.cashFlows = cashFlows;
	}

}
