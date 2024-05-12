package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Location;
import com.example.WebBanVe.repository.LocationRepository;
import com.example.WebBanVe.service.interf.ILocationService;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private LocationRepository locationRepo;

    @Override
    public List<Location> getAll() {
        return locationRepo.findAll();
    }

    @Override
    public List<Location> search(String province, String district, String ward) {
        return locationRepo.findLocationsByProvinceDistrictAndWard(province, district, ward);
    }

    @Override
    public Location getOne(Long id) {
        return locationRepo.findById(id).orElse(null); 
    }

    public boolean insert(Location location) {
        try {
            locationRepo.save(location);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Location location) {
        try {
            if (locationRepo.existsById(location.getId())) { 
                locationRepo.save(location);
                return true;
            } else {
                return false; 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            if (locationRepo.existsById(id)) { 
                locationRepo.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

	@Override
	public List<String> getProvince() {
		return locationRepo.findDistinctProvince();
	}

	@Override
	public List<String> getDistrict(String province) {
		return locationRepo.findDistinctDistrictByProvince(province);
	}

	@Override
	public List<String> getWard(String province, String district) {
		return locationRepo.findWardsByProvinceAndDistrict(province, district);
	}

}
