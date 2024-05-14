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

import com.example.WebBanVe.entity.Admin;
import com.example.WebBanVe.entity.Customer;
import com.example.WebBanVe.service.interf.IAdminService;

@RestController
@RequestMapping("/api")
public class AdminAPI {
	@Autowired
	private IAdminService service;
	@GetMapping("/admin")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/admin/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/admin")
	private ResponseEntity<?> insert(@RequestBody Admin admin){
		service.insert(admin);
		return ResponseEntity.ok(admin);
	}
	@PutMapping("/admin")
	private ResponseEntity<?> update(@RequestBody Admin admin){

		return ResponseEntity.ok(service.update(admin));
	}
	@DeleteMapping("/admin/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
}
