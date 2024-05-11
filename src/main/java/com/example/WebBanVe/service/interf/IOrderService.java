package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Order;

@Component
public interface IOrderService {
	List<Order> getAll();

	Order getOne(Long id);

	boolean insert(Order order);

	boolean update(Order order);

	boolean delete(Long id);
}