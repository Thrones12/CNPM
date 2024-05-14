package com.example.WebBanVe.entity;

import com.example.WebBanVe.Enumeration.eTicketClass;
import com.example.WebBanVe.Enumeration.eTicketStatus;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

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
	@Enumerated(EnumType.STRING)
    private eTicketStatus status;
    
    @Column(name = " ticket_class")
	@Enumerated(EnumType.STRING)
    private eTicketClass ticketClass;
 
    @OneToOne(mappedBy = "ticket")
    private Order order;
    

}