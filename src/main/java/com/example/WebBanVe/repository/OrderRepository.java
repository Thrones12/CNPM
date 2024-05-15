package com.example.WebBanVe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.Enumeration.eOrderStatus;
import com.example.WebBanVe.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomer_Id(Long customerId);

	List<Order> findByStatus(eOrderStatus paid);

	Order findByTicketId(Long ticketId);
}
