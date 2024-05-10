package com.example.WebBanVe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import org.joda.time.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="route")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="departure_id")
	private Station departure;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="arrival_id")
	private Station arrival;
	
	@Column(name="departure_time")
	private LocalDateTime departureTime;
	
	@Column(name="arrival_time")
	private LocalDateTime arrivalTime;

	public Route(Long id, String name, Station departure, Station arrival, LocalDateTime departureTime,
			LocalDateTime arrivalTime) {
		super();
		this.id = id;
		this.name = name;
		this.departure = departure;
		this.arrival = arrival;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public Route() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Station getDeparture() {
		return departure;
	}

	public void setDeparture(Station departure) {
		this.departure = departure;
	}

	public Station getArrival() {
		return arrival;
	}

	public void setArrival(Station arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}