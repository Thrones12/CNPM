package com.example.WebBanVe.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.service.interf.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerAPI {
	@Autowired
	private ICustomerService service;
	@GetMapping("/customer")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/customer/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/customer")
	private ResponseEntity<?> insert(@RequestBody Customer customer){
		service.insert(customer);
		return ResponseEntity.ok(customer);
	}
	@PutMapping("/customer")
	private ResponseEntity<?> update(@RequestBody Customer customer){

		return ResponseEntity.ok(service.update(customer));
	}
	@DeleteMapping("/customer/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
}
