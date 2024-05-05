package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.User;
import com.example.WebBanVe.repository.UserRepository;
import com.example.WebBanVe.service.interf.IUserService;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository repo; 

	@Override
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	public User getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
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
