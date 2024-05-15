package com.example.WebBanVe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User getByAccountUsername(String username);
}
