package com.example.WebBanVe.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanVe.service.interf.IUserService;

@RestController
@RequestMapping("/api")
public class UserAPI {
	@Autowired
	private IUserService service;
	@GetMapping("/user")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
}
