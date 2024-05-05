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

import com.example.WebBanVe.entity.Account;
import com.example.WebBanVe.service.interf.IAccountService;

@RestController
@RequestMapping("/api")
public class AccountAPI {
	@Autowired
	private IAccountService service;
	@GetMapping("/account")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/account/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/account")
	private ResponseEntity<?> insert(@RequestBody Account account){
		service.insert(account);
		return ResponseEntity.ok(account);
	}
	@PutMapping("/account")
	private ResponseEntity<?> update(@RequestBody Account account){

		return ResponseEntity.ok(service.update(account));
	}
	@DeleteMapping("/account/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
		
}
