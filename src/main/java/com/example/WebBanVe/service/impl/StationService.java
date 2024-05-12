package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.repository.StationRepository;
import com.example.WebBanVe.service.interf.IStationService;

import jakarta.transaction.Transactional;

@Service
public class StationService implements IStationService{
	@Autowired
	private StationRepository repo;
	
	@Override
	public List<Station> getAll() 
	{
		return repo.findAll();
	}

	@Override
	@Transactional
	public List<Station> getByType(eTransportType eType) {
		return repo.getStationByType(eType.toString());
	}
	@Override
	public Station getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Station station) {
		try {
			repo.save(station);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Station station) {
		try {
			repo.save(station);
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