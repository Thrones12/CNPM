package com.example.WebBanVe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Phong
@Entity
@Table(name="post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cus_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@Column(name="picture")
	private String picture;
	
	@Column(name="content")
	private String content;
	
	@Column(name="rating")
	private int rating;	
}
