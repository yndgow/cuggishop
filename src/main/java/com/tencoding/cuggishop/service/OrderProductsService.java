package com.tencoding.cuggishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.cuggishop.repository.interfaces.OrderProductsRepository;

@Service
public class OrderProductsService {

	@Autowired
	OrderProductsRepository orderProductsRepository;
}