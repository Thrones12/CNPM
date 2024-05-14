package com.example.WebBanVe.entity;
import java.time.LocalDateTime;

import com.example.WebBanVe.Enumeration.eOrderStatus;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@Column(name = "total_price")
	private Double totalPrice;

	@OneToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "passenger_id", referencedColumnName = "id")
	@JsonManagedReference
	private Passenger passenger;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private eOrderStatus status;
}