package com.example.WebBanVe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Admin;
import com.example.WebBanVe.entity.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin getByEmail(String email);

	Admin getByAccountUsername(String username);

}
