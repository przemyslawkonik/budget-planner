package com.github.przemyslawkonik.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.github.przemyslawkonik.entity.PaymentMethod;
import com.github.przemyslawkonik.repository.PaymentMethodRepository;

public class PaymentMethodConverter implements Converter<String, PaymentMethod> {
	@Autowired
	private PaymentMethodRepository paymentRepo;

	@Override
	public PaymentMethod convert(String source) {
		return paymentRepo.findOne(Integer.parseInt(source));
	}

}
