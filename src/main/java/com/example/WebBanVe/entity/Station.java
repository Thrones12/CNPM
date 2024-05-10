package com.example.WebBanVe.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "station")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
    
    @Column(name = "opening_time")
    private LocalDateTime openingTime;

    @Column(name = "closing_time")
    private LocalDateTime closingTime;

    @Column(name = "type")
    private ETransportType type;
    
    @OneToMany(mappedBy = "departure", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Route> departureRoutes;

    @OneToMany(mappedBy = "arrival", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Route> arrivalRoutes;

	public Station() {
		super();
	}

	public Station(Long id, String name, String location, LocalDateTime openingTime, LocalDateTime closingTime,
		ETransportType type) {
	super();
	this.id = id;
	this.name = name;
	this.location = location;
	this.openingTime = openingTime;
	this.closingTime = closingTime;
	this.type = type;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ETransportType getType() {
		return type;
	}

	public LocalDateTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalDateTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalDateTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalDateTime closingTime) {
		this.closingTime = closingTime;
	}

	public void setType(ETransportType type) {
		this.type = type;
	}
}
