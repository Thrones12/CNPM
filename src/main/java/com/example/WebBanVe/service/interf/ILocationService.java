package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Location;

@Component
public interface ILocationService {
	List<Location> getAll();

	Location getOne(Long id);

	boolean insert(Location location);

	boolean update(Location location);

	boolean delete(Long id);

}