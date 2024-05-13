package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Ticket;

@Component
public interface ITicketService {
	List<Ticket> getAll();
	List<Ticket> getAllstatus();
	List<Ticket> getAllstatusCr(Ticket ticket);
	Ticket getOne(Long id);

	boolean insert(Ticket ticket);

	boolean update(Ticket ticket);

	boolean delete(Long id);


}