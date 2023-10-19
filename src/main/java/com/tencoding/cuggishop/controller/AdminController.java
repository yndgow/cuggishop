package com.tencoding.cuggishop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.cuggishop.dto.request.ImgRequestDto;
import com.tencoding.cuggishop.dto.request.InsertOfflineStoreRequestDto;
import com.tencoding.cuggishop.dto.request.InsertPaymentRequestDto;
import com.tencoding.cuggishop.dto.request.InsertProductRequestDto;
import com.tencoding.cuggishop.dto.request.UpdateProductReqeustDto;
import com.tencoding.cuggishop.dto.request.UpdateUserDto;
import com.tencoding.cuggishop.dto.request.InsertQnaAnswerDto;
import com.tencoding.cuggishop.dto.request.UpdateOfflineStoreRequestDto;
import com.tencoding.cuggishop.dto.request.UpdateOrderListRequestDto;
import com.tencoding.cuggishop.dto.response.OrderListResponseDto;
import com.tencoding.cuggishop.dto.response.PaymentResponseDto;
import com.tencoding.cuggishop.dto.response.ProductListResponseDto;
import com.tencoding.cuggishop.dto.response.ProductResponseDto;
import com.tencoding.cuggishop.repository.model.User;
import com.tencoding.cuggishop.dto.response.AdminOrderDetailListResponseDto;
import com.tencoding.cuggishop.dto.response.AdminPageListDto;
import com.tencoding.cuggishop.dto.response.AdminProductResponseDto;
import com.tencoding.cuggishop.dto.response.OfflineStoreListResponseDto;
import com.tencoding.cuggishop.dto.response.OfflineStoreResponseDto;
import com.tencoding.cuggishop.dto.response.OrderBasketResponseDto;
import com.tencoding.cuggishop.dto.response.QnaAnswerResponseDto;
import com.tencoding.cuggishop.dto.response.QnaListResponseDto;
import com.tencoding.cuggishop.dto.response.UserInfoDetailDto;
import com.tencoding.cuggishop.dto.response.UserInfoListDto;
import com.tencoding.cuggishop.handler.exception.CustomRestfulException;
import com.tencoding.cuggishop.repository.model.FirstCategory;
import com.tencoding.cuggishop.repository.model.Qna;
import com.tencoding.cuggishop.repository.model.SecondCategory;
import com.tencoding.cuggishop.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	HttpSession session;

	@Autowired 
	ServletContext servletContext;
	
	//offlinestore start
	
	@GetMapping("offlineStoreManagement")
	public String offlineStoreManagement(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page, Model model) {
		AdminPageListDto<OfflineStoreListResponseDto> adminPageListDto = adminService.OfflineStoreList(type, keyword, page);
		model.addAttribute("adminPageListDto", adminPageListDto);
		return "admin/offlineStore/offlineStoreManagement"; 
	}	
	
	
	@GetMapping("insertOfflineStore")
	public String insertOfflineStore(Model model) {
		return "admin/offlineStore/offlineStoreInsert"; 
	}
	
	@PostMapping("insertOfflineStore")
	public String insertOfflineStore_proc(InsertOfflineStoreRequestDto insertOfflineStoreRequestDto) {
		if(insertOfflineStoreRequestDto.getStoreName() == null || insertOfflineStoreRequestDto.getStoreName().isEmpty()) {
			throw new CustomRestfulException("지점 이름을 입력해주세요", HttpStatus.BAD_REQUEST);
		}

		if(insertOfflineStoreRequestDto.getStoreNumber() == null) {
			throw new CustomRestfulException("지점 전화 번호를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		
		
		if(insertOfflineStoreRequestDto.getStoreAddress() == null || insertOfflineStoreRequestDto.getStoreAddress().isEmpty()) {
			throw new CustomRestfulException("지점 주소를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		
		if(insertOfflineStoreRequestDto.getStoreAddressDetail() == null || insertOfflineStoreRequestDto.getStoreAddressDetail().isEmpty()) {
			throw new CustomRestfulException("지점 상세주소를 입력해주세요", HttpStatus.BAD_REQUEST);
		}
		
		adminService.insertOfflineStore(insertOfflineStoreRequestDto);
		

		return "redirect:offlineStoreManagement"; 
	}
	
	@GetMapping("updateOfflineStore/{id}")
	public String updateOfflineStore(@PathVariable("id") int id, Model model) {
		OfflineStoreResponseDto offlineStoreResponseDto = adminService.findOfflineStoreById(id);
		model.addAttribute("offlineStoreResponseDto", offlineStoreResponseDto);
		return "admin/offlineStore/offlineStoreUpdate";
	}

	@PutMapping("updateOfflineStore")
	public String updateOfflineStore_proc(UpdateOfflineStoreRequestDto updateOfflineStoreRequestDto) {
		int result = adminService.updateOfflineStore(updateOfflineStoreRequestDto);
		return "redirect:offlineStoreManagement"; 
	}
	
	@GetMapping("deleteOfflineStore/{id}")
	public String deleteOfflineStore(@PathVariable("id") int id) {
		int result = adminService.deleteOfflineStore(id);
		return "redirect:admin	/offlineStoreManagement"; 
	}

	//offlinestore end
	
	// order start
	@GetMapping("orderListManagement")
	public String orderListManagent(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page,@RequestParam(required = false) String status, Model model) {
		
		AdminPageListDto<OrderListResponseDto> OrderadminPageListDto = adminService.OrderList(type, keyword, page,status);
		
		model.addAttribute("OrderadminPageListDto", OrderadminPageListDto);
		
		
		return "admin/order/orderManagement";
	}
	
	// orderId로 받아옴
	@GetMapping("updateOrderList/{id}")
	public String updateOrderList(@PathVariable("id") int id, Model model) {
		
		List<AdminOrderDetailListResponseDto> adminOrderDetailListResponseDto = adminService.findAdminOrderDetailList(id);
		model.addAttribute("adminOrderDetailListResponseDto", adminOrderDetailListResponseDto);
		
		PaymentResponseDto paymentResponseDto = adminService.findPayment(id);
		model.addAttribute("paymentResponseDto", paymentResponseDto);
							
		System.out.println(paymentResponseDto);		
		
		return "admin/order/orderListUpdate";
	}
	
	
	
	@PostMapping("updateOrder/{orderId}")
	public String insertPayment(@PathVariable("orderId") int orderId,UpdateOrderListRequestDto updateOrderRequestDto) {
		System.out.println("여긴?");
			
		adminService.updateOrder(updateOrderRequestDto, orderId);
		
		return "redirect:/admin/updateOrderList/"+orderId; 
	}
	
	
	
	
	@PostMapping("/cancelPayment/{orderId}")
	public String cancelPayment(@PathVariable("orderId") int orderId, Model model) {
		
		OrderListResponseDto orderListResponseDto = adminService.findOrderListById(orderId);
		model.addAttribute("orderListResponseDto", orderListResponseDto);
		
		PaymentResponseDto paymentResponseDto = adminService.findPayment(orderId);
		model.addAttribute("paymentResponseDto", paymentResponseDto);
		
		
		return "admin/order/cancelRequest_utf";
	}
	
	
	@GetMapping("/cancelPaymentResult/{orderId}")
	public String cancelPaymentResult(@PathVariable("orderId") int orderId) {
		
		return "admin/order/cancelResult_utf";
	}
	
	@PostMapping("/cancelPaymentResult/{orderId}")
	public String cancelPaymentResult(@PathVariable("orderId") int orderId,Model model) {
		OrderListResponseDto orderListResponseDto = adminService.findOrderListById(orderId);
		model.addAttribute("orderListResponseDto", orderListResponseDto);
		
		
		return "admin/order/cancelResult_utf";
	}
	
	
	
	
	// order end
  
	//qna start
	
	@GetMapping("/qnaList")
	public String qnaList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page,@RequestParam(required = false) String status, Model model) {
		AdminPageListDto<QnaListResponseDto> adminPageListDto = adminService.qnaList(type, keyword, page, status);
		model.addAttribute("adminPageListDto", adminPageListDto);
		return "admin/qna/qnaList";
	}
	
	@GetMapping("/qnaDetail/{id}")
	public String qnaDetail(@PathVariable int id, Model model) {
		QnaAnswerResponseDto qnaDetail = adminService.qnlDetail(id);
		model.addAttribute("qnaDetail", qnaDetail);
		return "admin/qna/qnaDetail";
	}
	
	@PostMapping("/qnaAnswer")
	public String qnaAswer(InsertQnaAnswerDto insertQnaAnswerDto) {
		int result = adminService.insertQnaAnswer(insertQnaAnswerDto);
		return "redirect:/admin/qnaList";
	}
	
	//qna end
	
	// product start
	/**
	 * 상품목록 페이지 이동
	 * @return 상품관리페이지 이동
	 */
	@GetMapping("/products")
	public String adminProductList(
			@RequestParam(required = false) String type, 
			@RequestParam(required = false) String keyword, 
			@RequestParam(defaultValue = "1") Integer page, 
			Model model) 
	{
		AdminPageListDto<ProductResponseDto> adminPageListDto = adminService.adminProductList(type, keyword, page);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("adminPageListDto", adminPageListDto);
		return "/admin/product/productManagement";
	}
	// 상품 상세 페이지
	@GetMapping("/product/{productId}")
	public String updateAdminProduct(@PathVariable String productId, Model model) {
		List<AdminProductResponseDto> adminProductList= adminService.findAdminProductResponseDtoByProductId(productId);
		model.addAttribute("adminProductList", adminProductList);
		List<SecondCategory> secondCategoryList = adminService.getSecondCategoryListByFirstCategoryId(adminProductList.get(0).getFcId());
		model.addAttribute("secondCategory", secondCategoryList);
		return "/admin/product/productUpdate";
	}
	// 상품 수정
	@PostMapping("/product/{productId}")
	public String updateAdminProductProc(@PathVariable Integer productId, 
			@RequestParam Map<String, MultipartFile> files, 
			UpdateProductReqeustDto updateProductReqeustDto) {
		
		String productName = updateProductReqeustDto.getProductName();
		String productFeature = updateProductReqeustDto.getProductFeature();
		int price = updateProductReqeustDto.getPrice();
		int quantity = updateProductReqeustDto.getQuantity();
//		String scName = updateProductReqeustDto.getScName();
		
		if(productName == null || productName.isEmpty()) throw new CustomRestfulException("상품 이름을 입력해주세요.", HttpStatus.BAD_REQUEST);
		if(productFeature == null || productFeature.isEmpty()) throw new CustomRestfulException("상품 설명을 입력해주세요.", HttpStatus.BAD_REQUEST);
		if(price <= 0) throw new CustomRestfulException("상품 가격을 올바르게 입력해주세요.", HttpStatus.BAD_REQUEST);
		if(quantity < 0) throw new CustomRestfulException("상품 수량을 올바르게 입력해주세요.", HttpStatus.BAD_REQUEST);
//		if(scName == null || scName.isEmpty()) throw new CustomRestfulException("2차 카테고리를 입력해주세요.", HttpStatus.BAD_REQUEST);
		
		// 상품 정보 업데이트
		adminService.updateProduct(updateProductReqeustDto);
				
		// 이미지 정보 업데이트
		adminService.updateProductImage(files, productId);
		
		return "redirect:/admin/product/" + productId;
	}
	
	// 2차 카테고리 리스트 1차카테고리 기준
	@ResponseBody
	@GetMapping("/product/category/first/{firstCategoryId}")
	public List<SecondCategory> secondCategoryList(@PathVariable int firstCategoryId){
		return adminService.getSecondCategoryListByFirstCategoryId(firstCategoryId);
	}
	// 상품 추가 페이지
	@GetMapping("/product")
	public String productInsert() {
		return "/admin/product/productInsert";
	}
	// 상품 추가 기능
	@PostMapping("/product")
	public String productInsertProc(InsertProductRequestDto insertProductRequestDto) {
		// 상품 정보 입력
		adminService.insertProduct(insertProductRequestDto);
		
		System.out.println();
		return "redirect:/admin/products/";
	}
	// 상품 삭제
	@GetMapping("/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable Integer productId) {
		adminService.deleteProduct(productId);
		return "redirect:/admin/products";
	}
	
	// 이미지 삭제
	@ResponseBody
	@DeleteMapping("/product/{productImageId}")
	public Map<String, Integer> deleteImg(@PathVariable Integer productImageId){
		int result = adminService.deleteImg(productImageId);
		Map<String, Integer> jsonData = new HashMap<>();
		jsonData.put("result", result);
		return jsonData;
	}
	
	// product end
	
	// user start
	@GetMapping(value={"","/userInfoList"})
	public String userInfoList(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1") Integer page,@RequestParam(required = false) String status, Model model) {
		AdminPageListDto<UserInfoListDto> adminPageListDto = adminService.userList(type, keyword, page, status);
		model.addAttribute("adminPageListDto", adminPageListDto);
		return "admin/user/userInfoList";
	}
	
	@GetMapping("/userInfoDetail/{id}")
	public String userInfoDetail(@PathVariable("id") int id, Model model) {
		UserInfoDetailDto userInfoDetailDto = adminService.userInfoDetail(id);
		model.addAttribute("userInfoDetail", userInfoDetailDto);
		return "admin/user/userInfoDetail";
	}
	
	@PostMapping("/userInfoDetail")
	public String userInfoDetail(UpdateUserDto updateUserDto) {
		int result = adminService.userInfoDetail(updateUserDto);
		return "redirect:/admin/userInfoList/";
	}
	
	@GetMapping("deleteUserInfo/{id}")
	public String deleteUserInfo(@PathVariable("id") int id) {
		int result = adminService.deleteUserInfo(id);
		return "redirect:/admin/userInfoList";
	}
}
