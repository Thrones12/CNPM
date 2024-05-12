package com.example.WebBanVe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account getByUsername(String username);

}
