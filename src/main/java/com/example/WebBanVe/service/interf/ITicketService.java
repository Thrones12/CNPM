package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Ticket;

@Component
public interface ITicketService {
	List<Ticket> getAll();

	Ticket getOne(Long id);

	boolean insert(Ticket ticket);

	boolean update(Ticket ticket);

	boolean delete(Long id);
	Ticket find(Long id, Long pasId, Long transId, Long routeId);

}