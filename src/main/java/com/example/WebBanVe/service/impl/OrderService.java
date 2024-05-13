package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.repository.OrderRepository;
import com.example.WebBanVe.repository.TicketRepository;
import com.example.WebBanVe.service.interf.IOrderService;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository repo;
    @Autowired
    private TicketRepository repoTC;
    

    @Override
    public List<Order> getAll() {
        return repo.findAll();
    }

    @Override
    public Order getOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean insert(Order order) {
        try {
            repo.save(order);
        
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Order order) {
        try {
        	Ticket ticket=repoTC.getOne(order.getTicket().getId());
            repo.save(order);
            repoTC.save(ticket);
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
}