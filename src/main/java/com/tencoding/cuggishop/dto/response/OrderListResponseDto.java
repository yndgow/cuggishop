package com.tencoding.cuggishop.dto.response;

import java.sql.Timestamp;

import com.tencoding.cuggishop.repository.model.OfflineStore;
import com.tencoding.cuggishop.repository.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponseDto {

	String image;
	String productName;
	int price;
	int id;
	int userId;
	Timestamp purchaseDate;
	Timestamp cancelDate;
	Timestamp createdAt;
	String name;
	String state;
	String username;

	public static OrderListResponseDto fromEntity(Order order) {
		System.out.println(order.getPrice());
		return new OrderListResponseDto(
				order.getImage(),
				order.getProductName(),
				order.getPrice(),
				order.getId(),
				order.getUserId(),
				order.getPurchaseDate(),
				order.getCancelDate(),
				order.getCreatedAt(),
				"",
				order.getState(),
				"");
	}

}