package com.tencoding.cuggishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.cuggishop.dto.response.PaymentDto;
import com.tencoding.cuggishop.repository.interfaces.PaymentRepository;
import com.tencoding.cuggishop.repository.model.Payment;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;


}
