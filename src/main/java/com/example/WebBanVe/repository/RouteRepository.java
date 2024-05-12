package com.example.WebBanVe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>
{
	@Query("SELECT r FROM Route r WHERE r.departure.id = :departureId AND r.arrival.id = :arrivalId")
    List<Route> findRoute(Long departureId, Long arrivalId);
}