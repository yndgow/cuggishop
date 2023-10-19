package com.tencoding.cuggishop.dto.response;

import lombok.Data;

@Data
public class CategoryResponseDto {
	int id;
	String secondCategoryName;
	int firstCategoryId;
	String firstCategoryName;
}
