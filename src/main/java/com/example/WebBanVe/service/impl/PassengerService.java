package com.example.WebBanVe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.repository.OrderRepository;
import com.example.WebBanVe.repository.PassengerRepository;
import com.example.WebBanVe.service.interf.IPassengerService;

import jakarta.persistence.criteria.Order;

@Service
public class PassengerService implements IPassengerService {
    @Autowired
    private PassengerRepository repo;
    
    @Autowired
    private OrderRepository repoOrder;
    @Override
    public List<Passenger> getAll() {
        return repo.findAll();
    }

    @Override
    public Passenger getOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean insert(Passenger passenger) {
        try {
            repo.save(passenger);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Passenger passenger) {
        try {
            repo.save(passenger);
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
	public List<Passenger> getNotInOrder() {
	  
	    List<Passenger> allPassengers = repo.findAll();
	
	    List<com.example.WebBanVe.entity.Order> allOrders = repoOrder.findAll();
	    List<Passenger> passengersNotInOrder = new ArrayList<>();

	    for (Passenger passenger : allPassengers) {
	        boolean foundInOrder = false;
	        for (com.example.WebBanVe.entity.Order order : allOrders) {

	            if (order.getPassenger().equals(passenger)) {
	                foundInOrder = true;
	                break;
	            }
	        }
	        if (!foundInOrder) {
	            passengersNotInOrder.add(passenger);
	        }
	    }
	    return passengersNotInOrder;
	}

	@Override
	public List<Passenger> getNotInOrder(Long id) {
			Passenger pass= repo.getOne(id);
		    List<Passenger> allPassengers = repo.findAll();
		
		    List<com.example.WebBanVe.entity.Order> allOrders = repoOrder.findAll();
		    List<Passenger> passengersNotInOrder = new ArrayList<>();
		  
		    for (Passenger passenger : allPassengers) {
		        boolean foundInOrder = false;	
		        for (com.example.WebBanVe.entity.Order order : allOrders) {

		            if (order.getPassenger().equals(passenger)) {
		                foundInOrder = true;
		                break;
		            }
		        }
		        if (!foundInOrder ||  passenger.equals(pass)) {
		            passengersNotInOrder.add(passenger);
		        }
		    }		   
	
		    return passengersNotInOrder;	
	}
	@Override
	public Passenger getLast() {
		return repo.findTopByOrderByIdDesc();
	}
 }
