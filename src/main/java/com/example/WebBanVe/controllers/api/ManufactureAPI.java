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

import com.example.WebBanVe.entity.Manufacture;
import com.example.WebBanVe.service.interf.IManufactureService;

@RestController
@RequestMapping("/api")
public class ManufactureAPI 
{
	@Autowired
	private IManufactureService service;
	@GetMapping("/manufacture")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/manufacture/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/manufacture")
	private ResponseEntity<?> insert(@RequestBody Manufacture manufacture){
		service.insert(manufacture);
		return ResponseEntity.ok(manufacture);
	}
	@PutMapping("/manufacture")
	private ResponseEntity<?> update(@RequestBody Manufacture manufacture){

		return ResponseEntity.ok(service.update(manufacture));
	}
	@DeleteMapping("/manufacture/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
}