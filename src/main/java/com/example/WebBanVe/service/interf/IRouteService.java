package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Route;

@Component
public interface IRouteService {
	List<Route> getAll();
	
	List<Route> search(Long departure_id, Long arrival_id);

	Route getOne(Long id);

	boolean insert(Route route);

	boolean update(Route route);

	boolean delete(Long id);
}