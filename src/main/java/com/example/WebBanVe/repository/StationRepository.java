package com.example.WebBanVe.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.example.WebBanVe.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long>
{
	@Procedure(name="getStationByType")
	List<Station> getStationByType(String station_type);
}