package com.example.WebBanVe.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.Enumeration.eOrderStatus;
import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.repository.OrderRepository;
import com.example.WebBanVe.service.interf.IOrderService;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository repo;

    @Override
    public List<Order> getAll() {
        return repo.findAll();
    }

	@Override
	public List<Order> getAllByCustomer(Long customer_id) {
		return repo.findByCustomer_Id(customer_id);
	}

    @Override
    public Order getOne(Long id) {
        return repo.findById(id).orElse(null);
    }

	@Override
	public Order getByTicketId(Long id) {
		return repo.findByTicketId(id);
	}

    @Override
    public boolean insert(Order order) {
        try {
            repo.save(order);
            repo.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Order order) {
        try {
            repo.save(order);
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
    public Map<String, Double> getMonthlyRevenueByYear(int year) {
        List<Order> paidOrders = repo.findByStatus(eOrderStatus.PAID);
        
        Map<String, Double> monthlyRevenue = new HashMap<>();

        for (Order order : paidOrders) {
            LocalDate orderDate = order.getOrderDate().toLocalDate();
            if (orderDate.getYear() == year) {
                String month = orderDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + orderDate.getYear();
                monthlyRevenue.merge(month, order.getTotalPrice(), Double::sum);
            }
        }

        return monthlyRevenue;
    }

    @Override
    public List<Integer> getAvailableYears() {
        List<Order> paidOrders = repo.findByStatus(eOrderStatus.PAID);

        return paidOrders.stream()
                .map(order -> order.getOrderDate().toLocalDate().getYear())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
