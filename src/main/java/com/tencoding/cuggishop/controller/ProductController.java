package com.tencoding.cuggishop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.cuggishop.dto.response.ProductResponseDto;
import com.tencoding.cuggishop.dto.response.ProductSearchListResponseDto;
import com.tencoding.cuggishop.dto.response.ProductListResponseDto;
import com.tencoding.cuggishop.repository.model.ProductImage;
import com.tencoding.cuggishop.service.ProductImageService;
import com.tencoding.cuggishop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageService productImageService;

	@Autowired
	HttpSession session;

	/**
	 * 제품목록 이동
	 * 필터시 매개변수 추가
	 * 
	 * @return 제품목록 페이지
	 */
	@GetMapping("list")
	public String productList(Integer firstCategoryId, Integer secondCategoryId,
			@RequestParam(defaultValue = "createAt", required = true) String filter,
			@RequestParam(required = false) String searchData,
			Model model) {
		// 서비스 호출
		// secondCategoryId = 1; // 임시 변수

		// 1. 제품 목록 가져오기
		Map<Integer, List<ProductListResponseDto>> productMap = productService.productList(firstCategoryId,
				secondCategoryId, filter, searchData);

		// 2. 2차 카테고리 이름 가져오기
		String secondCategoryName = "";
		String firstCategoryName = "";
		// 검색어가 없다면
		if (searchData == null || searchData.trim().isEmpty()) {
			// 반복문 한번에 탈출하기(맵의 리스트를 추출하여 2차카테고리명을 가져옴)
			loopOut: for (Map.Entry<Integer, List<ProductListResponseDto>> entry : productMap.entrySet()) {
				List<ProductListResponseDto> productListDtos = entry.getValue();
				for (ProductListResponseDto dto : productListDtos) {
					secondCategoryName = dto.getSecondCategoryName();
					firstCategoryName = dto.getFirstCategoryName();
					break loopOut;
				}
			}
		} else {
			secondCategoryName = "검색결과 : " + searchData;
		}

		// 목록 개수
		int productCount = productService.countProductListSize(firstCategoryId, secondCategoryId, filter, searchData);

		model.addAttribute("productCount", productCount);
		model.addAttribute("productMap", productMap);
		model.addAttribute("searchData", searchData);
		model.addAttribute("filter", filter);
		model.addAttribute("firstCategoryId", firstCategoryId);
		model.addAttribute("firstCategoryName", firstCategoryName);
		model.addAttribute("secondCategoryId", secondCategoryId);
		model.addAttribute("secondCategoryName", secondCategoryName);
		return "product/list";
	}

	@GetMapping("reloadList")
	@ResponseBody
	public Map<Integer, List<ProductListResponseDto>> reloadProductList(Integer firstCategoryId,
			Integer secondCategoryId,
			@RequestParam(defaultValue = "createAt", required = true) String filter,
			@RequestParam(required = false) String searchData, Integer startNum,
			Model model) {
		// 서비스 호출
		// secondCategoryId = 1; // 임시 변수
		// 1. 제품 목록 가져오기
		Map<Integer, List<ProductListResponseDto>> productMap = productService.reloadProductList(firstCategoryId,
				secondCategoryId, filter, searchData, startNum);

		// 2. 2차 카테고리 이름 가져오기
		String secondCategoryName = null;
		// 검색어가 없다면
		if (searchData == null || searchData.trim().isEmpty()) {
			// 반복문 한번에 탈출하기(맵의 리스트를 추출하여 2차카테고리명을 가져옴)
			loopOut: for (Map.Entry<Integer, List<ProductListResponseDto>> entry : productMap.entrySet()) {
				List<ProductListResponseDto> productListDtos = entry.getValue();
				for (ProductListResponseDto dto : productListDtos) {
					secondCategoryName = dto.getSecondCategoryName();
					break loopOut;
				}
			}
		} else {
			secondCategoryName = "검색결과 : " + searchData;
		}

		// 목록 개수
		int productCount = productMap.size();
		System.out.println(secondCategoryId);

		return productMap;
	}

	/**
	 * 제품상세정보 이동
	 * 
	 * @param productId
	 * @return 상세페이지
	 */
	@GetMapping("detail")
	public String productDetail(int productId, Model model) {
		// 서비스 호출
		// 1. 제품정보 가져오기
		// 2. 제품 이미지 가져오기
		// log.info("productId : " + productId);
		List<ProductResponseDto> productDto = productService.productDetail(productId);
		model.addAttribute("productList", productDto);

		// 3. 하단 썸네일 가져오기
		ProductImage productImage = productImageService.getFirstThumbnail(productId);
		model.addAttribute("productImage", productImage);
		return "product/detail";
	}

	// TODO 모두보기 ajax처리

	// 검색 -psg start
	@GetMapping("search")
	@ResponseBody
	public List<ProductSearchListResponseDto> SearchProductList(@RequestParam(defaultValue = "") String searchData) {
		System.out.println(searchData);
		// 서비스 호출
		// 1. 제품 목록 가져오기
		List<ProductSearchListResponseDto> searchList = productService.searchProductList(searchData);
		return searchList;
	}
	// 검색 -psg end
}
