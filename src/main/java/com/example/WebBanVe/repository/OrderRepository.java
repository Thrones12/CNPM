package com.example.WebBanVe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.entity.Post;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}