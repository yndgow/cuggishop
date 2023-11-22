package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.cuggishop.dto.response.AdminOrderDetailListResponseDto;
import com.tencoding.cuggishop.dto.response.OrderBasketResponseDto;
import com.tencoding.cuggishop.dto.response.OrderListResponseDto;
import com.tencoding.cuggishop.dto.response.PagingResponseDto;

import com.tencoding.cuggishop.repository.model.Order;

@Mapper
public interface OrderRepository {
	public int insert(Order order);

	public int updateById(Order order);

	public int deleteById(int orderId);

	public Order findById(int id);

	public List<Order> findByAll();

	public List<OrderListResponseDto> findByListAdmin();

	public Order findByDetailId(int id);

	public PagingResponseDto findPaging(@Param("type") String type, @Param("keyword") String keyword,
			@Param("page") Integer page, @Param("status") String status);

	public List<OrderListResponseDto> findByKeywordAndCurrentPage(@Param("type") String type,
			@Param("keyword") String keyword, @Param("startNum") Integer startNum, @Param("status") String status);

	public int orderDetailUpdate(Order order);

	public List<OrderBasketResponseDto> findByBasketList(int id);

	public int orderUpdate(Order order);

	public int orderAdminUpdate(Order order);

	public Order findByUserId(int userId);

	public int deleteBasket(int id);

	public List<OrderListResponseDto> findByList(int id);

	public List<AdminOrderDetailListResponseDto> findAdminOrderDetailId(int id);

	public List<Order> findByOrderAdminDetailCancelList(int orderId);

}
