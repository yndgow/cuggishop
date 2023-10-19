package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.cuggishop.dto.request.ImgRequestDto;
import com.tencoding.cuggishop.repository.model.ProductImage;

@Mapper
public interface ProductImageRepository {
	public int insert(List<ImgRequestDto> allImgList);
	public int updateById(List<ImgRequestDto> imgList);
	public int deleteById(int productImageId);
	public ProductImage findById(int productImageId);
	public List<ProductImage> findByAll();
	public ProductImage findByProductId(int productImageId);
	public int deleteByProductId(int productId);
}