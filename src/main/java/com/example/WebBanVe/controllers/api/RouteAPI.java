package com.example.WebBanVe.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.WebBanVe.entity.Route;
import com.example.WebBanVe.service.interf.IRouteService;

public class RouteAPI 
{
	@Autowired
	private IRouteService service;
	@GetMapping("/route")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/route/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/route")
	private ResponseEntity<?> insert(@RequestBody Route route){
		service.insert(route);
		return ResponseEntity.ok(route);
	}
	@PutMapping("/route")
	private ResponseEntity<?> update(@RequestBody Route route){

		return ResponseEntity.ok(service.update(route));
	}
	@DeleteMapping("/route/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
}