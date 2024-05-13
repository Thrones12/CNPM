package com.example.WebBanVe.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostDTO 
{
	private Long id;
	private Integer customerId;
	private Integer locationId;
	private String content;
	private MultipartFile picture;
	private Integer rating;
	public PostDTO(Long id, Integer customerId, Integer locationId, String content, MultipartFile picture,
			Integer rating) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.locationId = locationId;
		this.content = content;
		this.picture = picture;
		this.rating = rating;
	}
	public PostDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
