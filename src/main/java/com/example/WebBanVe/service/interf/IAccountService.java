package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Account;

@Component
public interface IAccountService {
	List<Account> getAll();

	Account getOne(Long id);

	boolean insert(Account account);

	boolean update(Account account);

	boolean delete(Long id);
}
