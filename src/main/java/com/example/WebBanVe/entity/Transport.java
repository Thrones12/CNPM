package com.example.WebBanVe.entity;

import com.example.WebBanVe.entity.Account.eAccountStatus;
import com.example.WebBanVe.entity.Account.eRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transport")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="manufacture")
	private Manufacture manufacture;
	
	@Column(name = "seating_capacity")
	private String seatingcapacity;
	
	@Column(name="type")
	private ETransportType type;	

	@Column(name="status")
	private eTransportStatus status;
	
	public enum eTransportStatus{
		STOPWORKING,
		MAINTENANCE,
		PREPARETODEPART,
		MOVING
	}
}