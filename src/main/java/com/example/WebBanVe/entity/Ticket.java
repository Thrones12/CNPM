package com.example.WebBanVe.entity;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;
 
    
    @Column(name = "reservation_code")
    private String reservationCode;

    @JsonIgnore
    @ManyToOne 
    @JoinColumn(name = "route_id")
    private Route route;
    
    @JsonIgnore
    @ManyToOne 
	@JoinColumn(name = "transport_id")
    private Transport transport;

    @Column(name = "status")
    private eStatus status;
    public enum eStatus {
    	STANDING, EXPIRED, USED, BOOKED;
	}
    @Column(name = " ticket_class")
    private TicketClass ticketClass;
    public enum TicketClass {
    	ECONOMY, BUSINESS, FIRST;
	}
 
    @OneToOne(mappedBy = "ticket")
    private Order order;
    




}