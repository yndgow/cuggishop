package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.cuggishop.dto.response.OrderDetailProductResponseDto;
import com.tencoding.cuggishop.repository.model.OrderProducts;

@Mapper
public interface OrderProductsRepository {
	public int insert(OrderProducts orderProducts);
	public int updateById(OrderProducts orderProducts);
	public int deleteById(int orderProductsId);
	public OrderProducts findById(int orderProductsId);
	public List<OrderProducts> findByAll();
	public List<OrderDetailProductResponseDto> findByDetailList(int orderId);
	public OrderDetailProductResponseDto findByDetailPerson(int orderId);
	public OrderDetailProductResponseDto findByDetailPayment(int orderId);
	public OrderDetailProductResponseDto updatePrice(OrderProducts orderProducts);
	public OrderProducts findByOrderIdAndProductId(@Param("orderId") int orderId,@Param("productId") int productId);
	public List<OrderProducts> findByOrderId(int orderId);
	public int updateByOrderId(OrderProducts orderProducts);
}
