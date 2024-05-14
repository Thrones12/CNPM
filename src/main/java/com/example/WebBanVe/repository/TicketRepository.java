package com.example.WebBanVe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByRouteId(Long routeId);
	@Query("SELECT t FROM Ticket t WHERE t.route.id = :routeId AND t.transport.id = :transportId AND t.status = 'ACTIVE' ORDER BY t.id ASC LIMIT 1")
    Ticket findTopByRouteIdAndTransportId(@Param("routeId") Long routeId, @Param("transportId") Long transportId);
}
