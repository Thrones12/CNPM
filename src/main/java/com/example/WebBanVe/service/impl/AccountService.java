package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Account;
import com.example.WebBanVe.repository.AccountRepository;
import com.example.WebBanVe.service.interf.IAccountService;

@Service
public class AccountService implements IAccountService{
	@Autowired
	private AccountRepository repo;
	
	@Override
	public List<Account> getAll() {
		return repo.findAll();
	}

	@Override
	public Account getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Account account) {
		try {
			repo.save(account);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Account account) {
		try {
			repo.save(account);
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
