package com.example.WebBanVe.entity;

import com.example.WebBanVe.Enumeration.eAccountStatus;
import com.example.WebBanVe.Enumeration.eAccountStatus;
import com.example.WebBanVe.Enumeration.eRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Phong
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
	@Enumerated(EnumType.STRING)
	private eAccountStatus status;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private eRole role;

	@JsonIgnore
	@OneToOne(mappedBy="account", cascade = CascadeType.ALL)
	private User user;
}
