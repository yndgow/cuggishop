package com.tencoding.cuggishop.dto.response;

import java.sql.Timestamp;

import com.tencoding.cuggishop.repository.model.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
	int id;
	String tid;
	int order_id;
	Timestamp created_at;

	public static PaymentResponseDto fromEntity(Payment payment) {
		return new PaymentResponseDto(
				payment.getId(),
				payment.getTid(),
				payment.getOrderId(),
				payment.getCreatedAt());

	}

}
