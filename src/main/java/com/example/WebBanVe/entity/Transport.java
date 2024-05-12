package com.example.WebBanVe.entity;

import com.example.WebBanVe.Enumeration.eTransportType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;

// Lâm
@Entity
@Table(name = "transport")
@Data
public class Transport 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="manufacture_id")
	private Manufacture manufacture;
	
	@Column(name = "seating_capacity")
	@Min(1)
	private int seatingcapacity;
	
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
    private eTransportType type;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private eTransportStatus status;
	
	public enum eTransportStatus {
		STOPWORKING, MAINTENANCE, PREPARETODEPART, MOVING
	}
}