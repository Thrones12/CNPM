package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.repository.RouteRepository;
import com.example.WebBanVe.service.interf.IRouteService;

@Service
public class RouteService implements IRouteService
{
	@Autowired
	private RouteRepository repo;
	
	@Override
	public List<Route> getAll() 
	{
		return repo.findAll();
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