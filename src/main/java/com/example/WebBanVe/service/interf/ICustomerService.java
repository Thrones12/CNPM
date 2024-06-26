package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Customer;

@Component
public interface ICustomerService {
	List<Customer> getAll();

	Customer getOne(Long id);

	Customer getByAccountUsername(String username);

	boolean insert(Customer customer);

	boolean update(Customer customer);

	boolean delete(Long id);

	Customer getOne(String email);

}
