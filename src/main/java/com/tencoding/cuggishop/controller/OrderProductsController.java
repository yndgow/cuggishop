package com.tencoding.cuggishop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.cuggishop.service.OrderProductsService;

@Controller
@RequestMapping("/orderProducts")
public class OrderProductsController {

	@Autowired
	OrderProductsService orderProductsService;

	@Autowired
	HttpSession session;		
}
