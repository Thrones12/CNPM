package com.example.WebBanVe.entity;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Kha
@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "price")
    private Double price;
   
    
    @Column(name = "passengerInfo")
    private String passengerInfo;
    
    @Column(name = "reservationCode")
    private String reservationCode;
     
  
    @Column(name = "routeID")
    private Long routeID;
    
    @Column(name = "transportID")
    private Long transportID;
    
    @Column(name = "status")
    private eStatus status;
    public enum eStatus {
    	STANDING,EXPIRED, USED, BOOKED
	}
    @Column(name = "ticketClass")
    private eStatus ticketClass;
    public enum ticketClass {
    	ECONOMY, BUSINESS, FIRST
	}

}