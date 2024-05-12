package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.Enumeration.eAccountStatus;
import com.example.WebBanVe.Enumeration.eRole;
import com.example.WebBanVe.entity.Account;
import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.repository.AccountRepository;
import com.example.WebBanVe.repository.CustomerRepository;
import com.example.WebBanVe.service.interf.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository repo;
	@Autowired
	private CustomerRepository cusRepo;

	@Override
	public List<Account> getAll() {
		return repo.findAll();
	}

	@Override
	public Account getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Account getOne(String username) {
		return repo.getByUsername(username);
	}

	@Override
	public boolean insert(Account account) {
		try {
			repo.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Account account) {
		try {
			repo.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean register(String username, String password, String email) {
		try {
			eAccountStatus status = eAccountStatus.ACTIVED;
			eRole role = eRole.CUSTOMER;
			new Account();
			Account account = Account.builder().username(username).password(password).status(status).role(role).build();
			repo.save(account);
			
			Customer customer = new Customer();
			customer.setAccount(account);
			cusRepo.save(customer);
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
