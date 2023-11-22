package com.tencoding.cuggishop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.cuggishop.dto.response.CategoryResponseDto;
import com.tencoding.cuggishop.repository.interfaces.SecondCategoryRepository;
import com.tencoding.cuggishop.repository.model.SecondCategory;

@Service
public class SecondCategoryService {

	@Autowired
	SecondCategoryRepository secondCategoryRepository;

	// 목록 조회
	public List<SecondCategory> getSecondCategoryList() {
		return secondCategoryRepository.findByAll();
	}

	public List<CategoryResponseDto> selectMenu() {
		List<CategoryResponseDto> secondCategoryList = secondCategoryRepository.findByMenu();
		return secondCategoryList;
	}

}
