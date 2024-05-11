package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.repository.PassengerRepository;
import com.example.WebBanVe.service.interf.IPassengerService;

@Service
public class PassengerService implements IPassengerService {
    @Autowired
    private PassengerRepository repo;

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
	
		return false;
	}
 }
