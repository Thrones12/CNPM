package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Admin;
import com.example.WebBanVe.entity.Customer;

@Component
public interface IAdminService {
	List<Admin> getAll();

	Admin getOne(Long id);

	Admin getByAccountUsername(String username);

	boolean insert(Admin admin);

	boolean update(Admin admin);

	boolean delete(Long id);

	Admin getOne(String email);

}
