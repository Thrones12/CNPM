package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Location;

@Component
public interface ILocationService {
	List<Location> getAll();

	List<Location> search(String province, String district, String ward);

	Location getOne(Long id);

	boolean insert(Location location);

	boolean update(Location location);

	boolean delete(Long id);
	
	List<String> getProvince();

	List<String> getDistrict(String province);

	List<String> getWard(String province, String district);

}