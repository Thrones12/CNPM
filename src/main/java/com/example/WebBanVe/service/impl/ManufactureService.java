package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Manufacture;
import com.example.WebBanVe.repository.ManufactureRepository;
import com.example.WebBanVe.service.interf.IManufactureService;

@Service
public class ManufactureService implements IManufactureService
{
	@Autowired
	private ManufactureRepository repo; 

	@Override
	public List<Manufacture> getAll() 
	{
		return repo.findAll();
	}

	@Override
	public Manufacture getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Manufacture manufacture) {
		try {
			repo.save(manufacture);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Manufacture manufacture) {
		try {
			repo.save(manufacture);
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