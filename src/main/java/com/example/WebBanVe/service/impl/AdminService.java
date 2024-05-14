package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Admin;
import com.example.WebBanVe.repository.AdminRepository;
import com.example.WebBanVe.service.interf.IAdminService;

@Service
public class AdminService implements IAdminService{
	@Autowired
	private AdminRepository repo; 

	@Override
	public List<Admin> getAll() {
		return repo.findAll();
	}

	@Override
	public Admin getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Admin getOne(String email) {
		return repo.getByEmail(email);
	}

	@Override
	public Admin getByAccountUsername(String username) {
		return repo.getByAccountUsername(username);
	}

	@Override
	public boolean insert(Admin admin) {
		try {
			repo.save(admin);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Admin admin) {
		try {
			repo.save(admin);
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
