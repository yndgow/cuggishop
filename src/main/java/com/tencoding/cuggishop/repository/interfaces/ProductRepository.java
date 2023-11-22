package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.cuggishop.dto.request.UpdateProductReqeustDto;
import com.tencoding.cuggishop.dto.response.AdminProductResponseDto;
import com.tencoding.cuggishop.dto.response.PagingResponseDto;
import com.tencoding.cuggishop.dto.response.ProductResponseDto;
import com.tencoding.cuggishop.dto.response.ProductSearchListResponseDto;
import com.tencoding.cuggishop.dto.response.ProductListResponseDto;
import com.tencoding.cuggishop.repository.model.Product;

@Mapper
public interface ProductRepository {
	public int insert(Product product);

	public int updateById(UpdateProductReqeustDto updateProductReqeustDto);

	public int deleteById(int productId);

	// public Product findById(String productId);
	public Product findById(int productId);

	public List<Product> findByAll();

	public List<ProductListResponseDto> findByAllForCateOrderByDesc(@Param("firstCategoryId") Integer firstCategoryId,
			@Param("secondCategoryId") Integer secondCategoryId, @Param("filter") String filter,
			@Param("searchData") String searchData);

	public List<ProductResponseDto> findByIdForCate(int productId);

	public PagingResponseDto findPaging(@Param("type") String type, @Param("keyword") String keyword,
			@Param("page") Integer page);

	public List<ProductResponseDto> findByKeywordAndCurrentPage(@Param("type") String type,
			@Param("keyword") String keyword, @Param("startNum") Integer startNum);

	public List<AdminProductResponseDto> findAdminProductByProductId(String productId);

	public List<ProductSearchListResponseDto> findBySearchProductAll(String searchData);
}
