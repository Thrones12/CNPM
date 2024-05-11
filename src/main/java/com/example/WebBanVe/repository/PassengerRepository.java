package com.example.WebBanVe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}