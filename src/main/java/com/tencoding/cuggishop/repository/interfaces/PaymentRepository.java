package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.tencoding.cuggishop.repository.model.Payment;

@Mapper
public interface PaymentRepository {
	public int insert(Payment payment);
	public int updateById(Payment payment);
	public int deleteById(int paymentId);
	public Payment findById(int paymentId);
	public List<Payment> findByAll();
	public Payment findPayment(int id);
	
}
