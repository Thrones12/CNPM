package com.example.WebBanVe.entity;

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

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private String status;
	@Column(name = "role")
	private String role;

	@JsonIgnore
	@OneToOne(mappedBy="account", cascade = CascadeType.ALL)
	private User user;
	
	public enum eRole {
		ADMIN, CUSTOMER
	}

	public enum eAccountStatus {
		INACTIVE, ACTIVED, LOCKED, DELETED
	}
}
