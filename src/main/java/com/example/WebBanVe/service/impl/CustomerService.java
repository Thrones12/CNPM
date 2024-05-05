package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.repository.CustomerRepository;
import com.example.WebBanVe.repository.UserRepository;
import com.example.WebBanVe.service.interf.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	@Autowired
	private CustomerRepository repo; 

	@Override
	public List<Customer> getAll() {
		return repo.findAll();
	}

	@Override
	public Customer getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Customer customer) {
		try {
			repo.save(customer);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Customer customer) {
		try {
			repo.save(customer);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
