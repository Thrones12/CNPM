package com.example.WebBanVe.entity;

import org.joda.time.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "opening_time")
	private DateTime openingtime;
	
	@Column(name = "closing_time")
	private DateTime closingtime;
	
	@Column(name = "type")
    private ETransportType type;
}