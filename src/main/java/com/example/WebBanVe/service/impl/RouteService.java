package com.example.WebBanVe.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.Utils.DateTimeConverter;
import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.repository.RouteRepository;
import com.example.WebBanVe.repository.TicketRepository;
import com.example.WebBanVe.service.interf.IRouteService;

import jakarta.transaction.Transactional;

@Service
public class RouteService implements IRouteService
{
	@Autowired
	private RouteRepository repo;
	@Autowired
	private TicketRepository ticketRepo;
	
	@Override
	public List<Route> getAll() 
	{
		return repo.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public List<Object[]> search(Long departure_id, Long arrival_id, String start, eTransportType eType) {
		LocalDate dateStart = DateTimeConverter.convertStringToLocalDate(start);
		List<Route> result = new ArrayList<Route>();
		return repo.searchTicket(departure_id, arrival_id, dateStart, eType.toString());
	}

	@Override
	public Route getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Route route) {
		try {
			repo.save(route);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Route route) {
		try {
			repo.save(route);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}