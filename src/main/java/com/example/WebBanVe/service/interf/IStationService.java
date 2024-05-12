package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.entity.Station;

@Component
public interface IStationService 
{
	List<Station> getAll();
	List<Station> getByType(eTransportType eType);
    Station getOne(Long id);
    boolean insert(Station station);
    boolean update(Station station);
    boolean delete(Long id);
}