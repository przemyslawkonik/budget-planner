package com.github.przemyslawkonik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.przemyslawkonik.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

}
