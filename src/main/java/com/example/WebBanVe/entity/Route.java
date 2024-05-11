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
}