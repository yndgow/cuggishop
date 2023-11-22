package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.cuggishop.repository.model.FirstCategory;

@Mapper
public interface FirstCategoryRepository {
	public int insert(FirstCategory firstCategory);

	public int updateById(FirstCategory firstCategory);

	public int deleteById(int firstCategoryId);

	public FirstCategory findById(int firstCategoryId);

	public List<FirstCategory> findByAll();
}
