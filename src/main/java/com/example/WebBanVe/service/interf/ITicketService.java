package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Ticket;

@Component
public interface ITicketService {
	List<Ticket> getAll();

	List<Ticket> getByTicket(Long route_id);

	Ticket getOne(Long id);

	Ticket getOne(Long route_id, Long transport_id);

	boolean insert(Ticket ticket);

	boolean update(Ticket ticket);

	boolean delete(Long id);

	List<Ticket> getAllstatusCr(Ticket ticketCr);

	List<Ticket> getAllstatus();

}
