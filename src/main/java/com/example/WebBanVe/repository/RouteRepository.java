package com.example.WebBanVe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>
{

}