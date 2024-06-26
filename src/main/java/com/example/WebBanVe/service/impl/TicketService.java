package com.example.WebBanVe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.Enumeration.eTicketStatus;
import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.repository.OrderRepository;
import com.example.WebBanVe.repository.TicketRepository;
import com.example.WebBanVe.service.interf.IRouteService;
import com.example.WebBanVe.service.interf.ITicketService;
import com.example.WebBanVe.service.interf.ITransportService;

@Service
public class TicketService implements ITicketService {

	@Override
	public List<Ticket> getByTicket(Long route_id) {
		return repo.findByRouteId(route_id);
	}

	@Autowired
	private TicketRepository repo;

	@Autowired
	private IRouteService routeService;
	@Autowired
	private ITransportService transportService;
	@Autowired
	private OrderRepository repoOrder;

	@Override
	public List<Ticket> getAll() {
		return repo.findAll();
	}

	@Override
	public Ticket getOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean insert(Ticket ticket) {
		try {
			repo.save(ticket);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Ticket ticket) {
		try {
			repo.save(ticket);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Ticket> getAllstatus() {

		List<Ticket> all = repo.findAll();
		List<Ticket> list = new ArrayList<>();
		List<com.example.WebBanVe.entity.Order> allOrders = repoOrder.findAll();
		for (Ticket ticket : all) {
			boolean foundInOrder = false;
			for (com.example.WebBanVe.entity.Order order : allOrders) {

				if (!ticket.getStatus().equals(eTicketStatus.ACTIVE) || order.getTicket().equals(ticket)) {
					foundInOrder = true;
					break;
				}
			}
			if (!foundInOrder) {
				list.add(ticket);
			}
		}

		return list;

	}

	@Override
	public List<Ticket> getAllstatusCr(Ticket ticketCr) {
		List<Ticket> all = repo.findAll();
		List<Ticket> list = new ArrayList<>();
		List<com.example.WebBanVe.entity.Order> allOrders = repoOrder.findAll();
		for (Ticket ticket : all) {
			boolean foundInOrder = false;
			for (com.example.WebBanVe.entity.Order order : allOrders) {

				if (!ticket.getStatus().equals(eTicketStatus.ACTIVE) || order.getTicket().equals(ticket)) {
					foundInOrder = true;
					break;
				}
			}
			if (!foundInOrder || ticket.equals(ticketCr)) {
				list.add(ticket);
			}
		}

		return list;

	}

	@Override
	public Ticket getOne(Long route_id, Long transport_id) {
		return repo.findTopByRouteIdAndTransportId(route_id, transport_id);
	}

}
