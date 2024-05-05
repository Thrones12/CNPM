package com.example.WebBanVe.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import groovy.transform.builder.Builder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name="name")
	private String name;

	@JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="gender")
	private eGender gender;
	
	@Column(name="birthdate")
	private Date birthdate;
	
	public enum eGender{
		MALE,
		FEMALE
	}
	
}
