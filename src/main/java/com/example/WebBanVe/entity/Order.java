package com.example.WebBanVe.entity;

import com.example.WebBanVe.entity.Ticket.eStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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

	@Column(name = "order_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;

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

	private eStatus status;

	public enum eStatus {
		PAID, UNPAID, REFUND;
	}
}