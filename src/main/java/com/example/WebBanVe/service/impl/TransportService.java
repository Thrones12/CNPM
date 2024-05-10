package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Transport;
import com.example.WebBanVe.repository.TransportRepository;
import com.example.WebBanVe.service.interf.ITransportService;

@Service
public class TransportService implements ITransportService
{
	@Autowired
	private TransportRepository repo; 

	@Override
	public List<Transport> getAll() 
	{
		return repo.findAll();
	}

	@Override
	public Transport getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Transport transport) {
		try {
			repo.save(transport);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Transport transport) {
		try {
			repo.save(transport);
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