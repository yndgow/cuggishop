package com.tencoding.cuggishop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tencoding.cuggishop.service.FirstCategoryService;

@Controller
@RequestMapping("/firstCategory")
public class FirstCategoryController {

	@Autowired
	FirstCategoryService FirstCategoryService;

	@Autowired
	HttpSession session;
}
