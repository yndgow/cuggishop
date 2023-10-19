package com.tencoding.cuggishop.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProducts {
	int id;
	int quantity;
	int orderId;
	int price;
	int productId;
	String productName;
	
	public OrderProducts(int orderId, Product product) {
		this.id = 0;
		this.quantity = 1;
		this.orderId = orderId;
		this.price = product.getPrice();
		this.productId = product.getId();
		this.productName = product.getProductName();
	}
}
