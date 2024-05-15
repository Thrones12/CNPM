package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Passenger;

@Component
public interface IPassengerService {
	List<Passenger> getAll();

	Passenger getOne(Long id);

	boolean insert(Passenger passenger);

	boolean update(Passenger passenger);

	boolean delete(Long id);
	List<Passenger> getNotInOrder(Long id);

	List<Passenger> getNotInOrder();

	Passenger getLast();
}
