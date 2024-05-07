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

import com.example.WebBanVe.entity.Station;
import com.example.WebBanVe.service.interf.IStationService;

@RestController
@RequestMapping("/api")
public class StationAPI 
{
	@Autowired
	private IStationService service;
	@GetMapping("/station")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/station/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/station")
	private ResponseEntity<?> insert(@RequestBody Station station){
		service.insert(station);
		return ResponseEntity.ok(station);
	}
	@PutMapping("/station")
	private ResponseEntity<?> update(@RequestBody Station station){

		return ResponseEntity.ok(service.update(station));
	}
	@DeleteMapping("/station/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
}