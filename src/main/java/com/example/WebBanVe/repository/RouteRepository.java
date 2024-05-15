package com.example.WebBanVe.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findByDepartureIdAndArrivalIdAndDepartureTime(Long departureId, Long arrivalId,
			LocalDate departureTime);

	@Procedure(name = "searchTicket")
	List<Object[]> searchTicket(Long departure_id, Long arrival_id, LocalDate departure_time, String type, String tcSelected);
}
