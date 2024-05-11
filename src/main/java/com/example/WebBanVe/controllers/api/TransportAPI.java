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

import com.example.WebBanVe.entity.Transport;
import com.example.WebBanVe.service.interf.ITransportService;

@RestController
@RequestMapping("/api")
public class TransportAPI 
{
	@Autowired
	private ITransportService service;
	@GetMapping("/transport")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/transport/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/transport")
	private ResponseEntity<?> insert(@RequestBody Transport transport){
		service.insert(transport);
		return ResponseEntity.ok(transport);
	}
	@PutMapping("/transport")
	private ResponseEntity<?> update(@RequestBody Transport transport){

		return ResponseEntity.ok(service.update(transport));
	}
	@DeleteMapping("/transport/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}

}