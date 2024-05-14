package com.example.WebBanVe.service.interf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Order;

@Component
public interface IOrderService {
	List<Order> getAll();
	
	List<Order> getAllByCustomer(Long customer_id);

	Order getOne(Long id);

	boolean insert(Order order);

	boolean update(Order order);

	boolean delete(Long id);

	Map<String, Double> getMonthlyRevenueByYear(int year);

	List<Integer> getAvailableYears();

	Order getByTicketId(Long id);
}